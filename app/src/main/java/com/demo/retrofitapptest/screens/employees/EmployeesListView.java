package com.demo.retrofitapptest.screens.employees;
/* Created by Ihor Bochkor on 11.10.2020.
 */

import com.demo.retrofitapptest.pojo.Employee;

import java.util.List;

public interface EmployeesListView {
    void showData(List<Employee> employees);
    void showError(Throwable throwable);
}
