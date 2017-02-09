package com.sukhjinder.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by Sukhjinder on 7/9/16.
 */
public class MovieFragment extends Fragment {

    private MovieAdapter mMovieAdapter;
    private ProgressBar mProgressBar;
    private GridView GridView;

    ArrayList<Movie> movies;
    int page = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.movie_list, container, false);
        GridView = (GridView) rootView.findViewById(R.id.list_movies);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mMovieAdapter = new MovieAdapter(getActivity(), R.layout.movie_list, new ArrayList<Movie>());
        new FetchMovieTask(mMovieAdapter, mProgressBar).execute("1");
        new FetchMovieTask(mMovieAdapter, mProgressBar).execute("1");

        GridView.setAdapter(mMovieAdapter);
        GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) GridView.getItemAtPosition(position);
                Intent i = new Intent(getContext(), MovieInfo.class);
                i.putExtra("movieInfo", movie);
                startActivity(i);
            }
        });

        return rootView;
    }
}