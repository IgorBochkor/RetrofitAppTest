package com.demo.retrofitapptest.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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


public class EmployeeLisActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private TextView textViewText;
    private TextView textViewIni;
    private EmployeeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewText = findViewById(R.id.textViewText);
        textViewIni = findViewById(R.id.textViewBash);
        recyclerView = findViewById(R.id.recycleView);

        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());//добавляємо в адаптер пустий список, щоб додаток не падав
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        viewModel  = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(EmployeeLisActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });
        viewModel.loadData();
    }

}