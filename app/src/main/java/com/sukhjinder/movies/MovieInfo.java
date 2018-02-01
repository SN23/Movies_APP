package com.sukhjinder.movies;

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

    private TextView movieOverview;
    private TextView movieReleaseDate;
    private ImageView movieBackdrop;
    private ImageView moviePoster;
    private FloatingActionButton playTrailerButton;
    private FloatingActionButton addToWatchListButton;
    private Movie movie;
    private static String BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w1280/";
    private static String BASE_URL_POSTER = "http://image.tmdb.org/t/p/w500/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        movieBackdrop = (ImageView) findViewById(R.id.movieBackdrop);
        moviePoster = findViewById(R.id.movie_poster);
        movieOverview = (TextView) findViewById(R.id.movieOverview);
        movieReleaseDate = (TextView) findViewById(R.id.movieReleaseDate);
        addToWatchListButton = findViewById(R.id.addToWatchList);


        movie = getIntent().getParcelableExtra("movieInfo");

        Picasso.with(getApplicationContext())
                .load(BASE_URL_BACKDROP + movie.getBackdrop_path())
                .fit()
                .centerCrop()
                .into(movieBackdrop);

        Picasso.with(getApplicationContext())
                .load(BASE_URL_POSTER + movie.getPoster_path())
                .into(moviePoster);

        getSupportActionBar().setTitle(movie.getTitle());

        movieOverview.setText(movie.getOverview());
        movieReleaseDate.setText(movie.getRelease_date());
        movieBackdrop.setAdjustViewBounds(true);
        moviePoster.setAdjustViewBounds(true);

        addToWatchListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movie);
                WatchListFragment watchListFragment = new WatchListFragment();
                watchListFragment.setArguments(bundle);
                Toast.makeText(getApplicationContext(), "Added To Watch List", Toast.LENGTH_SHORT).show();
            }
        });

        playTrailerButton = (FloatingActionButton) findViewById(R.id.PlayTrailer);
//        playTrailerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (movie.get().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Trailer not availble", Toast.LENGTH_SHORT).show();
//                } else {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movie.getTrailer())));
//                }
//            }
//        });
    }
}