package com.example.daggertest.di;


import com.example.daggertest.di.auth.AuthModule;
import com.example.daggertest.di.auth.AuthViewModelMudule;
import com.example.daggertest.di.main.MainFrahmentBuilderModule;
import com.example.daggertest.di.main.MainModule;
import com.example.daggertest.di.main.MainViewModelModule;
import com.example.daggertest.main.MainActivity;
import com.example.daggertest.ui.auth.AuthActivity;
import com.example.daggertest.ui.auth.AuthViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelMudule.class, AuthModule.class}
    )
    abstract AuthActivity contibuteAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFrahmentBuilderModule.class, MainViewModelModule.class, MainModule.class}
    )
    abstract MainActivity contibutrMainActivity();

}
