package com.example.daggertest.main.posts;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.daggertest.SessionManager;
import com.example.daggertest.network.main.MainApi;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {

    SessionManager sessionManager;
    MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
    }

}
