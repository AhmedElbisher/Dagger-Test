package com.example.daggertest.main.posts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.daggertest.R;
import com.example.daggertest.SessionManager;
import com.example.daggertest.main.Resource;
import com.example.daggertest.model.Post;
import com.example.daggertest.model.User;
import com.example.daggertest.ui.auth.AuthResource;
import com.example.daggertest.viewmodelfactory.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class PostsFragment extends DaggerFragment {
    @Inject
    SessionManager sessionManager;
    RecyclerView recyclerView;
    PostsViewModel viewModel;
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    PostRecyclerAdapter postRecyclerAdapter;


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
        initRecyclerAdaptr();
        observeUserID();
    }

    public  void  initRecyclerAdaptr(){
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(postRecyclerAdapter);
    }

    public void observeUserID(){
        sessionManager.getAuthUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){
                        case AUTHENTICATED:
                            subscribeobserver(userAuthResource.data.getId());
                            break;
                    }
                }
            }
        });
    }

    public void subscribeobserver(int userId){
        viewModel.obsevePosts(userId).removeObservers(getViewLifecycleOwner());
        viewModel.obsevePosts(userId).observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource != null){
                    switch (listResource.status){
                        case ERROR:
                            Log.i("TAG", listResource.message.toString());
                            break;
                        case SUCCESS:
                            Log.i("TAG", listResource.data.toString());
                            postRecyclerAdapter.setPosts(listResource.data);
                            break;
                        case LOADING:
                            Log.i("TAG", "Looding ");
                            break;
                    }
                }
            }
        });
    }
}