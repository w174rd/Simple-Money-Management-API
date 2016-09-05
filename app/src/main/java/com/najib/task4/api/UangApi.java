package com.najib.task4.api;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by w174rd on 9/5/2016.
 */

public interface UangApi {
    @GET("/api/v1/auth")
    Call<Uang> getUsers();

    @GET("/api/v1/auth/{id}")
    Call<Uang> getUser(@Path("id")String user_id);

    @PUT("/api/v1/auth/{id}")
    Call<Uang> updateUser(@Path("id") String user_id, @Body Uang user);

    @POST("/api/v1/auth")
    Call<Uang> saveUser(@Body Uang user);

    @DELETE("/api/v1/auth/{id}")
    Call<Uang> deleteUser(@Path("id") String user_id);
}