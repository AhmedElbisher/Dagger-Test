package com.example.daggertest.main.posts;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggertest.SessionManager;
import com.example.daggertest.main.Resource;
import com.example.daggertest.model.Post;
import com.example.daggertest.network.main.MainApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {

    MainApi mainApi;
    private MediatorLiveData<Resource<List<Post>>> posts;


    @Inject
    public PostsViewModel( MainApi mainApi) {
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<Post>>>  obsevePosts(int userId){
        if(posts == null){
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>)null));
            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPosts(userId)
                    .onErrorReturn(new Function<Throwable, List<Post>>() {
                        @Override
                        public List<Post> apply(Throwable throwable) throws Exception {
                            Post post = new Post();
                            post.setId(-1);
                            post.setBody(throwable.toString());
                            List<Post> posts = new ArrayList<>();
                            posts.add(post);
                            return posts;
                        }
                    })
                    .map(new Function<List<Post>, Resource<List<Post>>>() {
                        @Override
                        public Resource<List<Post>> apply(List<Post> posts) throws Exception {
                            if(posts.size() > 0 ){
                                if(posts.get(0).getId() == -1){
                                    return Resource.error(posts.get(0).getBody(),null);
                                }
                            }
                            return Resource.success(posts);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );

            posts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });

        }
        return posts;
    }

}
