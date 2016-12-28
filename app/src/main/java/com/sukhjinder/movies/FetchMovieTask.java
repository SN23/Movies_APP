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
import java.util.ArrayList;

/**
 * Created by Sukhjinder on 7/9/16.
 */
public class FetchMovieTask extends AsyncTask<String, Void, ArrayList<Movie>> {

    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    private MovieAdapter MovieAdapter;

    public FetchMovieTask(MovieAdapter movieAdapter) {
        this.MovieAdapter = movieAdapter;
    }

    int pageNum = 2;
    int totalPageNum=0;


    @Override
    protected ArrayList<Movie> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJsonStr = null;

        try {
            final String NEWS_BASE_URL = "http://api.themoviedb.org/3/movie/now_playing?";
            final String PAGENUM = "&page=";
            final String API_PARAM = "api_key";

            Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
                    .appendQueryParameter(PAGENUM,Integer.toString(pageNum))
                    .appendQueryParameter(API_PARAM, BuildConfig.TMDB_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());

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
    private ArrayList<Movie> getMovieDataFromJson(String newsJsonStr)
            throws JSONException {

        final ArrayList<Movie> movies = new ArrayList<>();

        final String RESULTS = "results";
        final String TITLE = "original_title";
        final String OVERVIEW = "overview";
        final String POSTER_PATH = "poster_path";
        final String BACKDROP_PATH = "backdrop_path";

        JSONObject movieJson = new JSONObject(newsJsonStr);
        JSONArray resultsArray = movieJson.getJSONArray(RESULTS);

        for (int i = 0; i < resultsArray.length(); i++) {
            String title;
            String overview;
            String poster;
            String backdrop;
            // Get the JSON object representing the movie
            JSONObject MovieInfo = resultsArray.getJSONObject(i);

            title = MovieInfo.getString(TITLE);
            overview = MovieInfo.getString(OVERVIEW);
            poster = MovieInfo.getString(POSTER_PATH);
            backdrop = MovieInfo.getString(BACKDROP_PATH);

            movies.add(new Movie(title, overview, poster, backdrop));
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movie) {
        if (movie != null) {
            MovieAdapter.clear();
            for (Movie movieInfo : movie) {
                MovieAdapter.add(movieInfo);
            }
        }
    }
}
