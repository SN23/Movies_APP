package com.sukhjinder.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TrailerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trailer_view);
        Context context = TrailerView.this;

        ArrayList<Trailer> trailers = getIntent().getParcelableArrayListExtra("movie_trailers");

        RecyclerView recyclerView = findViewById(R.id.trailer_view_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new TrailerViewAdapter(context, trailers));

    }

}
