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
    private String releaseDate;


    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getId() {
        return id;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public Movie(String title, String overview, String poster, String backdrop, String id, String trailer, String releaseDate) {
        this.overview = overview;
        this.title = title;
        this.poster = poster;
        this.backdrop = backdrop;
        this.trailer = trailer;
        this.id = id;
        this.releaseDate = releaseDate;

    }
}
