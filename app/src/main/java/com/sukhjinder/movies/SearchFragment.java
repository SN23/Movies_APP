package com.sukhjinder.movies;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sukhjinder.movies.api.MoviesAPI;
import com.sukhjinder.movies.model.Movie;
import com.sukhjinder.movies.model.MovieResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sukhjinder on 1/13/17.
 */

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText input;
    private Retrofit retrofit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.search, container, false);
        recyclerView = rootView.findViewById(R.id.search_recycler);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        String BASE_URL = "https://api.themoviedb.org/3/";
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Button searchButton = rootView.findViewById(R.id.searchButton);
        input = rootView.findViewById(R.id.searchEditText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(input.getText().equals(""))) {
                    String query = input.getText().toString().trim();
                    apiCall(query);
                    hideKeyboard(getActivity());
                }
            }
        });

        return rootView;
    }

    private void apiCall(String query) {
        MoviesAPI client = retrofit.create(MoviesAPI.class);
        Call<MovieResults> call = client.search(BuildConfig.TMDB_API_KEY, "en-US", query);

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                List<Movie> movieList = results.getResults();
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(new SearchAdapter(getContext(), movieList));
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
