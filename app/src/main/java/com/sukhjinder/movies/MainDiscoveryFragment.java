package com.sukhjinder.movies;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sukhjinder on 6/22/16.
 */
public class MainDiscoveryFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public class FetchMovieID extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchMovieID.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                final String TMDB_BASE_URL = "http://api.themoviedb.org/3/movie/popular?";
                final String apiKey = "api_key=";

                Uri builturi = Uri.parse(TMDB_BASE_URL).buildUpon()
                        .appendQueryParameter(apiKey, BuildConfig.TMDB_API_KEY)
                        .build();

                URL url = new URL(builturi.toString());

                Log.v(LOG_TAG, "Built URI" + builturi.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return new String[0];
        }
    }
}