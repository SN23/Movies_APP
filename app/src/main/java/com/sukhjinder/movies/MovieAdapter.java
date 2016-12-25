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


    public MovieAdapter(Context context, ArrayList<Movie> Movies) {
        super(context, 0, Movies);

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

//        viewHolder.movieTitleView.setText(movie.getTitle());
//        viewHolder.movieOverview.setText(movie.getOverview());

        viewHolder.moviePoster.setAdjustViewBounds(true);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500" + movie.getPoster()).into(viewHolder.moviePoster);

        return convertView;
    }

    private static final class ViewHolder {
//        private final TextView movieTitleView;
//        private final TextView movieOverview;
        private final ImageView moviePoster;

        public ViewHolder(View view) {
//            movieTitleView = (TextView) view.findViewById(R.id.movie_title);
//            movieOverview = (TextView) view.findViewById(R.id.movie_Overview);
            moviePoster = (ImageView) view.findViewById(R.id.moviePoster);
        }
    }
}