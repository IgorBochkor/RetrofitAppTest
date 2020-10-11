package com.demo.retrofitapptest.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.retrofitapptest.R;
import com.demo.retrofitapptest.adapter.EmployeeAdapter;
import com.demo.retrofitapptest.pojo.Employee;


import java.util.ArrayList;
import java.util.List;


public class EmployeeLisActivity extends AppCompatActivity implements EmployeesListView {
    private RecyclerView recyclerView;
    //    private PostsAdapter adapter;
    private EmployeeAdapter adapter;
    private TextView textViewText;
    private TextView textViewIni;
    private EmployeeListPresenter presenter;


    @Override
    protected void onDestroy() {
        presenter.disposable();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new EmployeeListPresenter(this);
        textViewText = findViewById(R.id.textViewText);
        textViewIni = findViewById(R.id.textViewBash);
        recyclerView = findViewById(R.id.recycleView);

        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());//добавляємо в адаптер пустий список, щоб додаток не падав
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter.loadData();
    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Network error! " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }


}