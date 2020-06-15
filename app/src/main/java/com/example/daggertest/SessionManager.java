
package com.example.daggertest;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.daggertest.model.User;
import com.example.daggertest.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void AuthuserWithId(final LiveData<AuthResource<User>> source){
        if(authUser != null) {
            authUser.setValue(AuthResource.loading((User)null ));
            authUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    authUser.setValue(userAuthResource);
                    authUser.removeSource(source);
                }
            });
        }
    }

    public  void logout(){
        authUser.setValue(AuthResource.<User>logout());
    }

    public  LiveData<AuthResource<User>> getAuthUser(){
        return authUser;
    }

}

