package com.sukhjinder.movies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by Sukhjinder on 1/13/17.
 */

public class MovieSearchFragment extends Fragment {

    private MovieSearchAdapter MovieSearchAdapter;
    private ProgressBar ProgressBar;
    ArrayList<Movie> movies;
    Button searchButton;
    EditText input;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MovieSearchAdapter = new MovieSearchAdapter(getActivity(), new ArrayList<Movie>());
        final View rootView = inflater.inflate(R.layout.search_movie_list, container, false);
        ProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        input = (EditText) rootView.findViewById(R.id.searchEditText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar.setVisibility(View.VISIBLE);
                if (!(input.getText().equals(""))) {
                    String query = input.getText().toString().trim();
                    MovieSearchAdapter.clear();
                    new FetchMovieSearch(MovieSearchAdapter, ProgressBar).execute(query);
                    hideKeyboard(getActivity());
                }
            }
        });

        final GridView gridView = (GridView) rootView.findViewById(R.id.searchList_movies);
        gridView.setAdapter(MovieSearchAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) gridView.getItemAtPosition(position);
                Intent i = new Intent(getContext(), MovieInfo.class);
                i.putExtra("movieInfo", movie);
                startActivity(i);
            }
        });

        return rootView;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
