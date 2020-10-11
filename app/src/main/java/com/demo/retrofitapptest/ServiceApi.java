package com.demo.retrofitapptest;


import com.demo.retrofitapptest.pojo.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ServiceApi {
    //    @GET("/api/get")
//    Call<List<PostModel>> getData(@Query("name") String resourceName, @Query("num") int number);
//    Observable<List<PostModel>> getData(@Query("name") String resourceName, @Query("num") int number);
    @GET("testTask.json")
//    Call<EmployeeResponse> getEmployees();
//    Call<EmployeeResponse> getEmployees();
    Observable<EmployeeResponse> getEmployees();
}
