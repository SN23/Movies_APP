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
 * Created by Sukhjinder on 1/13/17.
 */

public class MovieSearchAdapter extends ArrayAdapter<Movie> {


    public MovieSearchAdapter(Context context, ArrayList<Movie> Movies) {
        super(context, 0, Movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MovieSearchAdapter.ViewHolder viewHolder;
        final Movie movie = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_movie_item, parent, false);
            viewHolder = new MovieSearchAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MovieSearchAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.moviePoster.setAdjustViewBounds(true);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500" + movie.getPoster()).into(viewHolder.moviePoster);

        return convertView;
    }

    private static final class ViewHolder {
        private final ImageView moviePoster;

        public ViewHolder(View view) {
            moviePoster = (ImageView) view.findViewById(R.id.searchMoviePoster);
        }
    }
}
