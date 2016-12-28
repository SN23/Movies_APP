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

import java.util.ArrayList;

/**
 * Created by Sukhjinder on 7/9/16.
 */
public class MovieFragment extends Fragment {

    private MovieAdapter mMovieAdapter;
    ArrayList<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mMovieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.movie_list, container, false);
        final GridView gridView = (GridView) rootView.findViewById(R.id.list_movies);
        gridView.setAdapter(mMovieAdapter);
        gridView.setEmptyView(rootView.findViewById(R.id.text_no_movies));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) gridView.getItemAtPosition(position);
                Intent i = new Intent(getContext(), MovieInfo.class);
                i.putExtra("movieInfo", movie);
                startActivity(i);
            }
        });

        final EditText SearchMovies = (EditText) rootView.findViewById(R.id.search_movies);
        Button searchButton = (Button) rootView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            boolean result = false;

            @Override
            public void onClick(View v) {
                final String movieName = SearchMovies.getText().toString().trim();

                hideKeyboard(getActivity());
                mMovieAdapter.clear();
                new FetchMovieTask(mMovieAdapter).execute(movieName);
                result = true;
            }

        });

        return rootView;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
