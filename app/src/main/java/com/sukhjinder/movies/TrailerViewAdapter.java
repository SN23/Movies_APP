package com.sukhjinder.movies;

/**
 * Created by Sukhjinder on 2/2/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TrailerViewAdapter extends RecyclerView.Adapter<TrailerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Trailer> trailers;
    private int totalPages;
    private static String BASE_URL = "https://img.youtube.com/vi/";


    public TrailerViewAdapter(Context context, ArrayList<Trailer> trailers) {
        this.context = context;
        this.trailers = trailers;
    }

    @Override
    public TrailerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerViewAdapter.ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.trailer_thumbnail.setAdjustViewBounds(true);
        Picasso.with(context)
                .load(BASE_URL + trailers.get(position).getKey() + "/0.jpg")
                .into(viewHolder.trailer_thumbnail);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView trailer_thumbnail;

        public ViewHolder(View view) {
            super(view);
            trailer_thumbnail = view.findViewById(R.id.trailer_thumbnail);
        }
    }
}