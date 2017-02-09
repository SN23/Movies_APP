package com.sukhjinder.movies;

/**
 * Created by Sukhjinder on 7/9/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public MovieAdapter(Context context, int layoutResourceId, ArrayList<Movie> movies) {
        super(context, layoutResourceId, movies);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final Movie movie = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.moviePoster.setAdjustViewBounds(true);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500" + movie.getPoster()).into(viewHolder.moviePoster);

        return convertView;
    }

    private static final class ViewHolder {
        private final ImageView moviePoster;

        public ViewHolder(View view) {
            moviePoster = (ImageView) view.findViewById(R.id.moviePoster);
        }
    }
}