package com.example.daggertest.di.auth;


import androidx.lifecycle.ViewModel;

import com.example.daggertest.di.ViewModelKey;
import com.example.daggertest.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelMudule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public  abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);
}
