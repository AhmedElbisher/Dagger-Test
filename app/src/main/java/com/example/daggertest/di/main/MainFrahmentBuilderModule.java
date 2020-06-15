package com.example.daggertest.di.main;

import android.widget.ProgressBar;

import com.example.daggertest.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFrahmentBuilderModule {
    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
}
