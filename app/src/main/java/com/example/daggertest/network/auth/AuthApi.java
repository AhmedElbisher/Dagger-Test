package com.example.daggertest.network.auth;

import com.example.daggertest.model.User;

import io.reactivex.Flowable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {
    @GET("users/{id}")
    public Flowable<User> getUserInfo(@Path("id") int userId);
}
