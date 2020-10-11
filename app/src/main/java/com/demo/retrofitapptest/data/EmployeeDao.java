package com.demo.retrofitapptest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.demo.retrofitapptest.pojo.Employee;

import java.util.List;

/* Created by Ihor Bochkor on 11.10.2020.
 */
@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employees")
    LiveData<List<Employee>> getAllEmployees();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertEmployees(List<Employee> employees);

    @Query("DELETE FROM employees")
    void deleteAllEmployees();
}
