package com.project.m4u.rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.project.m4u.db.MyDbManager;
import com.project.m4u.domain.Actor;
import com.project.m4u.domain.Country;
import com.project.m4u.domain.Genre;
import com.project.m4u.domain.Movie;
import com.project.m4u.domain.mapper.ActorMapper;
import com.project.m4u.domain.mapper.CountryMapper;
import com.project.m4u.domain.mapper.GenreMapper;
import com.project.m4u.domain.mapper.MovieMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieApiImpl implements MovieApi {

    RequestQueue queue;
    MyDbManager myDbManager;
    private final Context context;
    JsonArrayRequest jsonArrayRequest;
    private Response.ErrorListener errorListener;
    public static final String BASE_URL = "http://*********:8080";


    public MovieApiImpl(Context context) {
        this.context = context;
        myDbManager = new MyDbManager(context);
        errorListener = new ErrorListenerImpl();
    }

    @Override
    public void fillCountry() {
        myDbManager.openDb();
        queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/country";
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Country country = new CountryMapper().countryFromJsonArray(jsonObject);
                                myDbManager.insertCountryDb(country);
                            }
                        } catch (JSONException e) {
                            Log.i("fillCountry", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }

    @Override
    public void fillGenre() {
        myDbManager.openDb();
        queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/genre";
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Genre genre = new GenreMapper().genreFromJsonArray(jsonObject);
                                myDbManager.insertGenreDb(genre);
                            }
                        } catch (JSONException e) {
                            Log.i("fillGenre", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }

    @Override
    public void fillActor() {
        myDbManager.openDb();
        queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/actor";
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Actor actor = new ActorMapper().actorFromJsonArray(jsonObject);
                                myDbManager.insertActorDb(actor);
                            }
                        } catch (JSONException e) {
                            Log.i("fillActor", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }

    @Override
    public void fillMovie() {
        myDbManager.openDb();
        queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/movie";
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                Country country = new CountryMapper().countryFromMovieJsonArray(jsonObject);
                                Genre genre = new GenreMapper().genreFromMovieJsonArray(jsonObject);
                                Actor actor = new ActorMapper().actorFromMovieJsonArray(jsonObject);
                                Movie movie = new MovieMapper().movieFromJsonArray(jsonObject,
                                        country.getId(),
                                        genre.getId(),
                                        actor.getId());

                                myDbManager.insertMovieToDb(movie);
                            }
                        } catch (JSONException e) {
                            Log.i("fillMovie", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }

    @Override
    public void updateMovie() {
        myDbManager.openDb();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/movie";
        myDbManager = new MyDbManager(context);
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                Country country = new CountryMapper().countryFromMovieJsonArray(jsonObject);
                                Genre genre = new GenreMapper().genreFromMovieJsonArray(jsonObject);
                                Actor actor = new ActorMapper().actorFromMovieJsonArray(jsonObject);
                                Movie movie = new MovieMapper().movieFromJsonArray(jsonObject,
                                        country.getId(),
                                        genre.getId(),
                                        actor.getId());

                                myDbManager.updateMovieDb(movie);
                            }
                        } catch (JSONException e) {
                            Log.d("updateMovie", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }

    @Override
    public void updateCountry() {
        myDbManager.openDb();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/country";
        myDbManager = new MyDbManager(context);
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Country country = new CountryMapper().countryFromJsonArray(jsonObject);
                                myDbManager.updateCountryDb(country);
                            }
                        } catch (JSONException e) {
                            Log.i("updateCountry", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }

    @Override
    public void updateGenre() {
        myDbManager.openDb();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/genre";
        myDbManager = new MyDbManager(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Genre genre = new GenreMapper().genreFromJsonArray(jsonObject);
                                myDbManager.updateGenreDb(genre);
                            }
                        } catch (JSONException e) {
                            Log.i("updateGenre", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }

    @Override
    public void updateActor() {
        myDbManager.openDb();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/actor";
        myDbManager = new MyDbManager(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Actor actor = new ActorMapper().actorFromJsonArray(jsonObject);
                                myDbManager.updateActorDb(actor);
                            }
                        } catch (JSONException e) {
                            Log.i("updateActor", e.getMessage());
                        }
                    }
                },
                errorListener
        );

        queue.add(jsonArrayRequest);
        myDbManager.closeDb();
    }


    private class ErrorListenerImpl implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("ErrorConnection", "Error");
        }
    }
}
