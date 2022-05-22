package com.project.m4u.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    public static final String BASE_URL = "http://192.168.1.67:8080";

    private final Context context;

    private Response.ErrorListener errorListener;
    MyDbManager myDbManager;

    public MovieApiImpl(Context context) {
        this.context = context;
        myDbManager = new MyDbManager(context);
        errorListener = new ErrorListenerImpl();
    }

    @Override
    public void fillCountry() {
        myDbManager.openDb();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/country";
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

                                Country country = new CountryMapper().countryFromJsonArray(jsonObject);
                                myDbManager.insertCountryDb(country);
//                                Toast.makeText(context, Integer.toString(myDbManager.getCountriesFromDb().size()), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(context, country.getName(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.d("COUNTRIES", e.getMessage());
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
                                myDbManager.insertGenreDb(genre);
//                                Toast.makeText(context, genre.getName(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.d("GENRES", e.getMessage());
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
                                myDbManager.insertActorDb(actor);
                            }
                        } catch (JSONException e) {
                            Log.d("COUNTRIES", e.getMessage());
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
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/movie";
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

                                Movie movie = new MovieMapper().movieFromJsonArray(jsonObject);;
                                myDbManager.insertToDb(movie);
//                                Toast.makeText(context, movie.getName() + "FILLMDB", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.d("BOOKS", e.getMessage());
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
            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
}
