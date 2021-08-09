package com.example.absensiandroid.service;

import com.example.absensiandroid.entity.Absensi;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AbsensiInterface {

    @GET("/absen/{username}")
    Call<ArrayList<Absensi>> getAbsenByUsername(@Path("username") String username);

    @Multipart
    @POST("/absen/checkin")
    Call<ResponseBody> checkInAbsen(@Part MultipartBody.Part file, @Part("data") RequestBody data);

    @Multipart
    @POST("/absen/checkout")
    Call<ResponseBody> checkOutAbsen(@Part MultipartBody.Part file, @Part("data") RequestBody data);
}
