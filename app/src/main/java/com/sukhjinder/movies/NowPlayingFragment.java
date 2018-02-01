package com.sukhjinder.movies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sukhjinder on 7/9/16.
 */
public class NowPlayingFragment extends Fragment {

    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private RecyclerView recyclerView;
    private View rootView;
    private Retrofit retrofit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiCall();
//        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.now_playing, container, false);
        recyclerView = rootView.findViewById(R.id.now_playing_recycler);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        return rootView;
    }

    private void apiCall() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesAPI client = retrofit.create(MoviesAPI.class);
        Call<MovieResults> call = client.getNowPlaying(BuildConfig.TMDB_API_KEY, "en-US", 1);

        call.enqueue(new Callback<MovieResults>() {

            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
//                int totalPages = results.getTotal_pages();
                List<Movie> movieList = results.getResults();
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(new NowPlayingAdapter(getContext(), movieList));
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }

        });
    }
}