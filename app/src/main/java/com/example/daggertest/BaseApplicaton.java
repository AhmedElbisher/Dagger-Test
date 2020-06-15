package com.example.daggertest;

import com.example.daggertest.di.DaggerAppComponant;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class BaseApplicaton extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponant.builder().application(this).build();
    }
}
