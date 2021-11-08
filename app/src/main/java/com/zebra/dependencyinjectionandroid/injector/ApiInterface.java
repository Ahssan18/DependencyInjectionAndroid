package com.zebra.dependencyinjectionandroid.injector;

import com.zebra.dependencyinjectionandroid.injector.models.Post;
import com.zebra.dependencyinjectionandroid.modules.ApiEndpoints;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @Singleton
    @GET(ApiEndpoints.posts)
    Call<List<Post>> getPosts();
}
