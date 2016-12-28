package com.sukhjinder.movies;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieOverview = (TextView) findViewById(R.id.movieOverview);
        movieBackdrop = (ImageView) findViewById(R.id.movieBackdrop);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=frdj1zb9sMY")));
            }
        });

        Movie movie = (Movie) getIntent().getSerializableExtra("movieInfo");
        movieTitle.setText(movie.getTitle());
        movieOverview.setText(movie.getOverview());
        movieBackdrop.setAdjustViewBounds(true);
        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w1280/" + movie.getBackdrop()).into(movieBackdrop);
    }
}
