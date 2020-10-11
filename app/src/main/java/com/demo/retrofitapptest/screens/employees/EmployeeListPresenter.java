package com.demo.retrofitapptest.screens.employees;
/* Created by Ihor Bochkor on 11.10.2020.
 */

import com.demo.retrofitapptest.api.Api;
import com.demo.retrofitapptest.api.ServiceApi;
import com.demo.retrofitapptest.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {

    private EmployeesListView view;
    Disposable disposable;

    public EmployeeListPresenter(EmployeesListView view) {
        this.view = view;
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
                        view.showData(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(throwable);
                    }
                });
    }

    public void disposable() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
