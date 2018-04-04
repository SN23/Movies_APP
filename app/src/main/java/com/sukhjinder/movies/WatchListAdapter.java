package com.sukhjinder.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sukhjinder on 2/1/18.
 */

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.ViewHolder> {

    private List<Movie> movies;
    private Movie movie;
    private Context context;
    private int totalPages;
    private static String BASE_URL = "http://image.tmdb.org/t/p/w500";


    public WatchListAdapter(Context context, Movie movie) {
        this.context = context;
        this.movie = movie;

    }

    @Override
    public WatchListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.watch_list_item, viewGroup, false);
        return new WatchListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WatchListAdapter.ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.movie_poster.setAdjustViewBounds(true);
//        Picasso.with(context)
//                .load(BASE_URL + movie.getPosterPath())
//                .into(viewHolder.movie_poster);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, movie.getTitle(), Toast.LENGTH_LONG).show();

                Intent i = new Intent(context, MovieInfo.class);
                i.putExtra("movieInfo", movie);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView movie_poster;

        public ViewHolder(View view) {
            super(view);
            movie_poster = view.findViewById(R.id.movie_poster);
        }
    }

}