package com.example.daggertest.di.main;


import androidx.lifecycle.ViewModel;

import com.example.daggertest.di.ViewModelKey;
import com.example.daggertest.main.posts.PostsViewModel;
import com.example.daggertest.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel  getProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel  getPostsViewModel(PostsViewModel postsViewModel);
}
