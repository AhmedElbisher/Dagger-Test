package com.example.daggertest.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggertest.SessionManager;
import com.example.daggertest.model.User;
import com.example.daggertest.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel  extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager=sessionManager;
    }

    LiveData<AuthResource<User>> getAuthinticatedUser(){
        return  sessionManager.getAuthUser();
    }

}
