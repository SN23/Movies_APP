package com.sukhjinder.movies;

/**
 * Created by Sukhjinder on 7/9/16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movies;
    //    private int totalPages;
    private static String BASE_URL = "http://image.tmdb.org/t/p/w500";


    public NowPlayingAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public NowPlayingAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.now_playing_movie_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NowPlayingAdapter.ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.movie_poster.setAdjustViewBounds(true);
        Picasso.with(context)
                .load(BASE_URL + movies.get(position).getPosterPath())
                .into(viewHolder.movie_poster);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = movies.get(pos);

                Intent i = new Intent(context, MovieInfo.class);
                i.putExtra("movieInfo", movie);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView movie_poster;

        public ViewHolder(View view) {
            super(view);
            movie_poster = view.findViewById(R.id.movie_poster);
        }
    }
}