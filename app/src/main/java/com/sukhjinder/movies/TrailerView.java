package com.sukhjinder.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TrailerView extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trailer_view);

        ArrayList<Trailer> trailers = getIntent().getParcelableArrayListExtra("movie_trailers");

        recyclerView = findViewById(R.id.trailer_view_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new TrailerViewAdapter(getApplicationContext(), trailers));

    }

}
