package com.example.daggertest.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.daggertest.R;
import com.example.daggertest.main.MainActivity;
import com.example.daggertest.model.User;
import com.example.daggertest.viewmodelfactory.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    EditText userInId;
    ProgressBar progressBar;
    Button loginButton;
    AuthViewModel viewModel;
    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    RequestManager glide;
    @Inject
    Drawable logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userInId = findViewById(R.id.user_id_input);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);
        loginButton.setOnClickListener(this);
        glide.load(logo).into((ImageView) findViewById(R.id.login_logo));
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);
        subscibeonobservers();

    }
    public  void subscibeonobservers(){
        viewModel.observeUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case AUTHENTICATED:
                            progressBar.setVisibility(View.INVISIBLE);
                            navToMainActivity();
                            break;
                        case NOT_AUTHENTICATED:
                            Log.i("Tag1", "not registered");
                            progressBar.setVisibility(View.INVISIBLE);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(AuthActivity.this, userAuthResource.message, Toast.LENGTH_SHORT).show();
                            break;

                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                attemAuth();
                break;
        }
    }
    public void navToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void attemAuth() {
        if(TextUtils.isEmpty(userInId.getText().toString())){
            return;
        }
        viewModel.usthenticateWithID(Integer.parseInt(userInId.getText().toString()));
    }
}
