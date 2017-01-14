package com.sukhjinder.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        mMovieAdapter.clear();
        new FetchMovieTask(mMovieAdapter).execute("1");
        new FetchMovieTask(mMovieAdapter).execute("1");
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
//        gridView.setOnScrollListener(new EndlessScrollListener() {
//            @Override
//            public boolean onLoadMore(int page, int totalItemsCount) {
//                // Triggered only when new data needs to be appended to the list
//                // Add whatever code is needed to append new items to your AdapterView
//                new FetchMovieTask(mMovieAdapter).execute(Integer.toString(page));
//                // or loadNextDataFromApi(totalItemsCount);
//                return true; // ONLY if more data is actually being loaded; false otherwise.
//            }
//        });


        // Append the next page of data into the adapter
        // This method probably sends out a network request and appends new data items to your adapter.

//    public void loadNextDataFromApi(int offset) {
//        // Send an API request to retrieve appropriate paginated data
//        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
//        //  --> Deserialize and construct new model objects from the API response
//        //  --> Append the new data objects to the existing set of items inside the array of items
//        //  --> Notify the adapter of the new items made with `notifyDataSetChanged()`
//            new FetchMovieTask(mMovieAdapter).execute(Integer.toString(offset));
//    }


        return rootView;
    }
}
