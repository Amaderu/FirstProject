package com.example.firstproject;

import com.example.firstproject.api.PetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostmanApi {

    @GET("user/{username}")
    public Call<PostModel.Swagger> getUser(@Path("username") String username);

    @POST("user")
    public Call<PostModel.Swagger> createUser(@Body PostModel.Swagger body);

    @PUT("user/{username}")
    Call<PostModel.Swagger> putUser(@Path("username") String username, @Body PostModel.Swagger body);

    @DELETE("user/{username}")
    public Call<PostModel.Swagger> delUser(@Path("username") String username);

    @GET("posts/{id}")
    Call<PostModel.Post> getPost(@Path("id") int id);

    @GET("pet/{petId}")
    Call<PetResponse> getPetbyId(@Path("petId") int petId);

    @GET("pet/findByStatus")
    Call<List<PetResponse>> findByStatus(@Query("status") String status);
}
