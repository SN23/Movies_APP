package com.sukhjinder.movies;

import java.io.Serializable;

/**
 * Created by Sukhjinder on 7/9/16.
 */
public class Movie implements Serializable {
    private String overview;
    private String title;
    private String poster;
    private String backdrop;
    private String id;
    private String trailer;


    public String getTrailer() {
        return trailer;
    }

    public String getId() {
        return id;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }


    public Movie(String title, String overview, String poster, String backdrop, String id, String trailer) {
        this.overview = overview;
        this.title = title;
        this.poster = poster;
        this.backdrop = backdrop;
        this.trailer = trailer;
        this.id = id;
    }
}
