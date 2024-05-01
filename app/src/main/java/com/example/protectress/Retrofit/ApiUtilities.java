package com.example.protectress.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiUtilities {

    String BASE_URL="https://newsapi.org/v2/";

    //show headlines just for home category
    @GET("top-headlines")
    //for home fragment
    Call<ModalClass> getNews(
            @Query("country") String country,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apiKey

    );
    // types of category
    @GET("top-headlines")
    Call<ModalClass> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apiKey
    );
}
