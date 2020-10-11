package com.demo.retrofitapptest.screens.employees;
/* Created by Ihor Bochkor on 11.10.2020.
 */

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.retrofitapptest.api.Api;
import com.demo.retrofitapptest.api.ServiceApi;
import com.demo.retrofitapptest.data.AppDatabase;
import com.demo.retrofitapptest.pojo.Employee;
import com.demo.retrofitapptest.pojo.EmployeeResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends AndroidViewModel {
    private static AppDatabase db;
    private LiveData<List<Employee>> employees;
    private MutableLiveData<Throwable> errors;
    private Disposable disposable;

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        employees = db.employeeDao().getAllEmployees();
        errors = new MutableLiveData<>();
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public void clearErrors(){
        errors.setValue(null);
    }

    @SuppressWarnings("unchecked")
    public void insertEmployees(List<Employee> employees){
        new InsertEmployeesTask().execute(employees);
    }

    private static class InsertEmployeesTask extends AsyncTask<List<Employee>,Void, Void>{

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Employee>... lists) {
            if (lists != null && lists.length > 0){
                db.employeeDao().insertEmployees(lists[0]);
            }
            return null;
        }
    }

    private void deleteAllEmployees(){
        new DeleteAllEmployeesTask().execute();
    }

    private static class DeleteAllEmployeesTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            db.employeeDao().deleteAllEmployees();
            return null;
        }
    }


    public void loadData() {
        Api api = Api.getApi();
        ServiceApi serviceApi = api.getServiceApi();
        disposable = serviceApi.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                            deleteAllEmployees();
                            insertEmployees(employeeResponse.getEmployees());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.setValue(throwable);

                    }
                });
    }

    @Override
    protected void onCleared() {
        disposable.dispose();
        super.onCleared();
    }
}
