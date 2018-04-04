package com.sukhjinder.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sukhjinder on 12/26/16.
 */

public class MovieInfo extends AppCompatActivity {

    private Movie movie;
    private static String BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w1280/";
    private static String BASE_URL_POSTER = "http://image.tmdb.org/t/p/w500/";
    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private Context context;
    private List<Trailer> trailers;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        context = MovieInfo.this;
        ImageView movieBackdrop = findViewById(R.id.movieBackdrop);
        ImageView moviePoster = findViewById(R.id.movie_poster);
        TextView movieOverview = findViewById(R.id.movieOverview);
        TextView movieReleaseDate = findViewById(R.id.movieReleaseDate);
        FloatingActionButton addToWatchListButton = findViewById(R.id.addToWatchList);
        FloatingActionButton playTrailerButton = findViewById(R.id.PlayTrailer);

        movie = getIntent().getParcelableExtra("movieInfo");

        apiCall((movie.getId()));

        Picasso.with(context)
                .load(BASE_URL_BACKDROP + movie.getBackdropPath())
                .fit()
                .centerCrop()
                .into(movieBackdrop);

        Picasso.with(context)
                .load(BASE_URL_POSTER + movie.getPosterPath())
                .into(moviePoster);

        getSupportActionBar().setTitle(movie.getTitle());

        movieOverview.setText(movie.getOverview());
        movieReleaseDate.setText(movie.getReleaseDate());
        movieBackdrop.setAdjustViewBounds(true);
        moviePoster.setAdjustViewBounds(true);

        addToWatchListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WatchListFragment watchListFragment = new WatchListFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("watch_list_item", movie);
                watchListFragment.setArguments(bundle);

                Toast.makeText(getApplicationContext(), "Added To Watch List", Toast.LENGTH_SHORT).show();
            }
        });

        playTrailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, TrailerView.class);
                i.putParcelableArrayListExtra("movie_trailers", (ArrayList<Trailer>) trailers);
                context.startActivity(i);
            }
        });
    }


    private void apiCall(int movieId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesAPI client = retrofit.create(MoviesAPI.class);
        Call<TrailerResults> call = client.getTrailer(movieId, BuildConfig.TMDB_API_KEY, "en-US");

        call.enqueue(new Callback<TrailerResults>() {

            @Override
            public void onResponse(Call<TrailerResults> call, Response<TrailerResults> response) {
                TrailerResults results = response.body();
                trailers = results.getResults();
            }

            @Override
            public void onFailure(Call<TrailerResults> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }


}