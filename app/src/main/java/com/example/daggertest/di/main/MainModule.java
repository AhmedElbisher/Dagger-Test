package com.example.daggertest.di.main;


import android.app.Application;

import androidx.navigation.NavOptions;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.daggertest.main.posts.PostRecyclerAdapter;
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
    @Provides
    public PostRecyclerAdapter  getPostRecycler(){
        return new PostRecyclerAdapter();
    }

    @Provides
    public LinearLayoutManager getLinearLayoutManager(Application application){
        return new LinearLayoutManager(application);
    }

}
