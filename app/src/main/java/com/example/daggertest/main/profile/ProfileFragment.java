package com.example.daggertest.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.daggertest.R;
import com.example.daggertest.model.User;
import com.example.daggertest.ui.auth.AuthResource;
import com.example.daggertest.viewmodelfactory.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    TextView email , username , webside;
    ProfileViewModel viewModel;
    @Inject
    ViewModelProviderFactory  factory;

    @Inject
    public ProfileFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        Toast.makeText(getContext(), "profile Fragment", Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel.class);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        webside = view.findViewById(R.id.website);
        subsribeObservers();

    }
     public void subsribeObservers(){
        viewModel.getAuthinticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthinticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){
                        case ERROR:
                            email.setText(userAuthResource.message);
                            username.setText(userAuthResource.message);
                            webside.setText(userAuthResource.message);
                            break;
                        case AUTHENTICATED:
                            email.setText(userAuthResource.data.getEmail());
                            username.setText(userAuthResource.data.getUsername());
                            webside.setText(userAuthResource.data.getWebsite());
                            break;
                    }
                }
            }
        });
     }
}