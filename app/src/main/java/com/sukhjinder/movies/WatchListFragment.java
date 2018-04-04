package com.sukhjinder.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sukhjinder on 2/8/17.
 */

public class WatchListFragment extends Fragment {

    //    private WatchListAdapter watchListAdapter = null;
    private Movie movie;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("watch_list_item")) {
            movie = bundle.getParcelable("watch_list_item");
//                Log.d("MOVIE", movie.getTitle());
        }


        View rootView = inflater.inflate(R.layout.watch_list, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.watch_list_recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new WatchListAdapter(getContext(), movie));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }
}