package com.example.absensiandroid.service;

import com.example.absensiandroid.entity.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginInterface {
    @GET("/login/{name}&{password}")
    Call<ArrayList<Login>> loginAbsensi(@Path("name") String name, @Path("password") String pass);
}
