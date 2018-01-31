package com.sukhjinder.movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sukhjinder on 1/31/18.
 */

public interface MoviesAPI {

    @GET("movie/now_playing")
    Call<MovieResults> getNowPlaying(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("search/movie?")
    Call<MovieResults> search(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

}