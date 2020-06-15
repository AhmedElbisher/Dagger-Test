package com.example.daggertest.di.main;


import com.example.daggertest.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    public   MainApi getmainAp(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
