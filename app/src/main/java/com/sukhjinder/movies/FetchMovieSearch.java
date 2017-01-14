package com.sukhjinder.movies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sukhjinder on 1/13/17.
 */

public class FetchMovieSearch extends AsyncTask<String, Void, ArrayList<Movie>> {

    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    private MovieSearchAdapter MovieSearchAdapter;

    public FetchMovieSearch(MovieSearchAdapter movieSearchAdapter) {
        this.MovieSearchAdapter = movieSearchAdapter;
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJsonStr = null;

        try {
            final String BASE_URL = "https://api.themoviedb.org/3/search/movie?";
            final String LANGUAGE = "language";
            final String API_PARAM = "api_key";
            final String QUERY = "query";


            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(API_PARAM, BuildConfig.TMDB_API_KEY)
                    .appendQueryParameter(LANGUAGE, "en-US")
                    .appendQueryParameter(QUERY, params[0])
                    .build();

            URL url = new URL(builtUri.toString());
            Log.d("URL", url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            movieJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getMovieDataFromJson(movieJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    //      JSON parser
    private ArrayList<Movie> getMovieDataFromJson(String movieJsonStr)
            throws JSONException {

        final ArrayList<Movie> movies = new ArrayList<>();

        final String RESULTS = "results";
        final String TITLE = "original_title";
        final String OVERVIEW = "overview";
        final String POSTER_PATH = "poster_path";
        final String BACKDROP_PATH = "backdrop_path";
        final String ID = "id";
        final String RELEASEDATE = "release_date";

        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray resultsArray = movieJson.getJSONArray(RESULTS);

        for (int i = 0; i < resultsArray.length(); i++) {
            String title;
            String overview;
            String poster;
            String backdrop;
            String id;
            String trailer;
            String releaseDate;

            // Get the JSON object representing the movie
            JSONObject MovieInfo = resultsArray.getJSONObject(i);

            id = MovieInfo.getString(ID);
            backdrop = MovieInfo.getString(BACKDROP_PATH);
            title = MovieInfo.getString(TITLE);
            overview = MovieInfo.getString(OVERVIEW);
            poster = MovieInfo.getString(POSTER_PATH);

            releaseDate = MovieInfo.getString(RELEASEDATE);
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");
            Date date = null;
            if (!(releaseDate.equals(""))) {
                try {
                    date = inputFormat.parse(releaseDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                releaseDate = outputFormat.format(date);
            } else {
                releaseDate = "";
            }
            trailer = new FetchTrailerTask().doInBackground(id);


            movies.add(new Movie(title, overview, poster, backdrop, id, trailer, releaseDate));
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movie) {
        if (movie != null) {
            MovieSearchAdapter.clear();
            for (Movie movieInfo : movie) {
                MovieSearchAdapter.add(movieInfo);
            }
        }
    }
}