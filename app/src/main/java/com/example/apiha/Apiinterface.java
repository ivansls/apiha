package com.example.apiha;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Apiinterface {
    @GET("get_user/{user}")
    Call<ArrayList<Users>> getUser(@Path("user") String user);


    @GET("getHash/{password}/{salt}")
    Call<String> getHash(@Path("password") String password, @Path("salt") String salt);

    @GET("getServices?page=1&size=50")
    Call<ArrayList<Service>> getServices();

    @POST("register_user/{login}/{password}/{name}/{salt}")
    Call<ArrayList<Users>> auth_user(@Path("login") String login, @Path("password") String password, @Path("name") String name, @Path("salt") String salt);
}
