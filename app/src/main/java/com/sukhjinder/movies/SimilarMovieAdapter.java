package com.sukhjinder.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sukhjinder on 1/10/17.
 */

public class SimilarMovieAdapter extends ArrayAdapter<Movie> {


    public SimilarMovieAdapter(Context context, ArrayList<Movie> Movies) {
        super(context, 0, Movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SimilarMovieAdapter.ViewHolder viewHolder;
        final Movie movie = getItem(position);
        View view = convertView;


        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.similar_movie_item, parent, false);
            viewHolder = new SimilarMovieAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SimilarMovieAdapter.ViewHolder) view.getTag();
        }

        viewHolder.moviePoster.setAdjustViewBounds(true);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500" + movie.getPosterPath()).into(viewHolder.moviePoster);

        return convertView;
    }

    private static final class ViewHolder {
        private final ImageView moviePoster;

        public ViewHolder(View view) {
            moviePoster = (ImageView) view.findViewById(R.id.similarMoviePoster);
        }
    }
}