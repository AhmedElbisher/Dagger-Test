package com.example.daggertest.main.posts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daggertest.R;
import com.example.daggertest.viewmodelfactory.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class PostsFragment extends DaggerFragment {


    RecyclerView recyclerView;
    PostsViewModel viewModel;
    @Inject
    ViewModelProviderFactory factory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this,factory).get(PostsViewModel.class);
        Toast.makeText(getContext(), "Posts fragment", Toast.LENGTH_SHORT).show();

    }
}