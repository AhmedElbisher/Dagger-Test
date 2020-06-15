package com.example.daggertest.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daggertest.R;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    public ProfileFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        Toast.makeText(getContext(), "profile Fragment", Toast.LENGTH_SHORT).show();
        return v;
    }
}