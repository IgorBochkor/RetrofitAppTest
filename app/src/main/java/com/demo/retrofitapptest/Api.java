package com.demo.retrofitapptest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Api api;
    private Retrofit retrofit;

    private Api(){
        retrofit = new Retrofit.Builder()
//                .baseUrl("https://umorili.herokuapp.com")
                .baseUrl("http://gitlab.65apps.com/65gb/static/raw/master/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Api getApi(){
        if (api == null){
            api = new Api();
        }
        return api;
    }

    public ServiceApi getServiceApi(){
        return retrofit.create(ServiceApi.class);
    }
}
