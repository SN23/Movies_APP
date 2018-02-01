package com.sukhjinder.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sukhjinder on 1/13/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;
    private static String BASE_URL = "http://image.tmdb.org/t/p/w500";

    public SearchAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.now_playing_movie_item, viewGroup, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder viewHolder, int position) {

        final int pos = position;

        Picasso.with(context)
                .load(BASE_URL + movies.get(position).getPoster_path())
                .into(viewHolder.movie_poster);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, movies.get(pos).getPoster_path(), Toast.LENGTH_LONG).show();
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
