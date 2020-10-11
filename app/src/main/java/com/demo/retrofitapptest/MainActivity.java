package com.demo.retrofitapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.retrofitapptest.adapter.EmployeeAdapter;
import com.demo.retrofitapptest.pojo.Employee;
import com.demo.retrofitapptest.pojo.EmployeeResponse;


import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    //    private PostsAdapter adapter;
    private EmployeeAdapter adapter;
    private TextView textViewText;
    private TextView textViewIni;
    Disposable disposable;

    @Override
    protected void onDestroy() {
        if (disposable !=null) {
        disposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewText = findViewById(R.id.textViewText);
        textViewIni = findViewById(R.id.textViewBash);
        recyclerView = findViewById(R.id.recycleView);
//        final ArrayList<PostModel> postModels = new ArrayList<>();
//        adapter = new PostsAdapter(postModels);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());//добавляємо в адаптер пустий список, щоб додаток не падав
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Api api = Api.getApi();
        ServiceApi serviceApi = api.getServiceApi();
        disposable = serviceApi.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        adapter.setEmployees(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


//        serviceApi.getEmployees().subscribeOn(Schedulers.io())

//        serviceApi.getEmployees().enqueue(new Callback<EmployeeResponse>() {
//            @Override
//            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
//                adapter.setEmployees(response.body().getResponse());
//            }
//
//            @Override
//            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
//            }
//        });


//        Api api = Api.getApi();
//        ServiceApi serviceApi = api.getServiceApi();
//        serviceApi.getData("bash",7).enqueue(new Callback<List<PostModel>>() {
//            @Override
//            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//                postModels.addAll(response.body());
//                recyclerView.getAdapter().notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<PostModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });


//        serviceApi.getData("bash",5)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<PostModel>>() {
//                    @Override
//                    public void accept(List<PostModel> postModels) throws Exception {
//                        adapter.setPost(postModels);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                });


    }
}