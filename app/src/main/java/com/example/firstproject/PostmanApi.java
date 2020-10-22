package com.example.firstproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostmanApi {

    @GET("user/{username}")
    public Call<PostModel.Swagger> getUser(@Path("username") String username);

    @POST("user")
    public Call<List<PostModel.Swagger>> usersList(@Body PostModel users);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("posts/{id}")
    Call<PostModel.Post> getPost(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @POST("user")
    Call<PostModel.Swagger> createUser(@Body PostModel.Swagger user);
}
