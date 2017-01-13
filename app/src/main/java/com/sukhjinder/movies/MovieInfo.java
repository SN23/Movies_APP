package com.sukhjinder.movies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Sukhjinder on 12/26/16.
 */

public class MovieInfo extends AppCompatActivity {

    TextView movieTitle;
    TextView movieOverview;
    TextView movieReleaseDate;
    ImageView movieBackdrop;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        movieBackdrop = (ImageView) findViewById(R.id.movieBackdrop);
        movieOverview = (TextView) findViewById(R.id.movieOverview);
        movieReleaseDate = (TextView) findViewById(R.id.movieReleaseDate);


        final Movie movie = (Movie) getIntent().getSerializableExtra("movieInfo");
        Picasso.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w1280/" + movie.getBackdrop())
                .fit()
                .centerCrop()
                .into(movieBackdrop);
        getSupportActionBar().setTitle(movie.getTitle());

        movieOverview.setText(movie.getOverview());
        movieReleaseDate.setText(movie.getReleaseDate());
        movieBackdrop.setAdjustViewBounds(true);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movie.getTrailer().equals("")) {
                    Toast.makeText(getApplicationContext(), "Trailer not availble", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movie.getTrailer())));
                }
            }
        });
    }
}