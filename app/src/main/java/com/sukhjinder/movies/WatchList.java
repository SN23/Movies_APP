package com.sukhjinder.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Sukhjinder on 2/2/17.
 */

public class WatchList extends AppCompatActivity {

    ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addToList(Movie movie) {
        movieList.add(movie);
    }

    public String GetInfo(){
        return movieList.get(0).getTitle();
    }
}
