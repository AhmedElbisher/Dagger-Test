package com.example.daggertest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.daggertest.model.User;
import com.example.daggertest.ui.auth.AuthActivity;
import com.example.daggertest.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public  SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();
    }

    void subscribeObserver(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            break;
                        case AUTHENTICATED:
                            Log.i("Tag1", userAuthResource.data.getEmail());

                            break;
                        case NOT_AUTHENTICATED:
                            navToAuthActivity();
                            Log.i("Tag1", "not registered");
                            break;
                        case ERROR:
                            break;

                    }
                }
            }
        });
    }

    public  void  navToAuthActivity(){
        Intent intent = new Intent(this,AuthActivity.class);
        startActivity(intent);
    }

}
