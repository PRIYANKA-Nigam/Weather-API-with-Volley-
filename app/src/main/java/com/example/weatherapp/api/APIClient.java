package com.example.weatherapp.api;

import com.example.weatherapp.Food.Response.SearchRecipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIClient {
    @GET("/api/recipes/v2")
    Call<SearchRecipe> searchRecipe(@Query("type")String type,@Query("q")String query,@Query("app_id")String appId,@Query("app_key")String appKey);
}
