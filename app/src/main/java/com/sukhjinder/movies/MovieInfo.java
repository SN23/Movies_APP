package com.sukhjinder.movies;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Sukhjinder on 12/26/16.
 */

public class MovieInfo extends AppCompatActivity {

    TextView movieTitle;
    TextView movieOverview;
    ImageView movieBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieOverview = (TextView) findViewById(R.id.movieOverview);
        movieBackdrop = (ImageView) findViewById(R.id.movieBackdrop);

        Movie movie = (Movie) getIntent().getSerializableExtra("movieInfo");
        movieTitle.setText(movie.getTitle());
        movieOverview.setText(movie.getOverview());
        movieBackdrop.setAdjustViewBounds(true);
        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w1280/" + movie.getBackdrop()).into(movieBackdrop);
    }
}
