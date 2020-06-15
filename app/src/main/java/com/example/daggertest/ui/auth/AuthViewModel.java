package com.example.daggertest.ui.auth;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggertest.SessionManager;
import com.example.daggertest.model.User;
import com.example.daggertest.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    AuthApi authApi;
    SessionManager sessionManager;


    @Inject
    public AuthViewModel(AuthApi authApi ,SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.authApi = authApi;
    }

    public void usthenticateWithID( int id){
        sessionManager.AuthuserWithId(quaryUserId(id));
    }


    public  LiveData<AuthResource<User>> quaryUserId( int id){
        return   LiveDataReactiveStreams.fromPublisher(
                authApi.getUserInfo(id)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User user = new User();
                                Log.i(TAG, throwable.toString());
                                user.setId(-1);
                                return user;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId() == -1){
                                    return AuthResource.error("error in log in",(User)null);
                                }else{
                                    return AuthResource.authenticated(user);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> observeUser() {
        return  sessionManager.getAuthUser();
    }
}
