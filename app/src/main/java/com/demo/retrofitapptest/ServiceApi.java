package com.demo.retrofitapptest;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("/api/get")
    Call<List<PostModel>> getData(@Query("name") String resourceName, @Query("num") int number);
//    Observable<List<PostModel>> getData(@Query("name") String resourceName, @Query("num") int number);
}
