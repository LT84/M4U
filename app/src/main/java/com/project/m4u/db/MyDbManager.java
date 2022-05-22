package com.project.m4u.db;

import static com.project.m4u.db.MyConstants.COUNTRY_NAME;
import static com.project.m4u.db.MyConstants.TABLE_NAME_COUNTRY;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.m4u.domain.Actor;
import com.project.m4u.domain.Country;
import com.project.m4u.domain.Genre;
import com.project.m4u.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {

    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;
    ContentValues cv;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public void openDb() {
        db = myDbHelper.getReadableDatabase();
    }

    public void insertToDb(Movie movie) {
        openDb();
        cv = new ContentValues();
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, movie.getName());
        cv.put(MyConstants.YEAR, movie.getYear());
        cv.put(MyConstants.PICURL, movie.getPicUrl());
        cv.put(MyConstants.DESCRIPTION, movie.getDescription());
        db.insert(MyConstants.TABLE_NAME_MOVIE, null, cv);
        closeDb();
    }

    public void insertCountryDb(Country country) {
        openDb();
        cv = new ContentValues();
        cv.put(COUNTRY_NAME, country.getName());
        db.insert(TABLE_NAME_COUNTRY, null, cv);
        closeDb();
    }

    public void insertGenreDb(Genre genre) {
        openDb();
        cv = new ContentValues();
        cv.put(MyConstants.GENRE_NAME, genre.getName());
        db.insert(MyConstants.TABLE_NAME_GENRE, null, cv);
        closeDb();
    }

    public void insertActorDb(Actor actor) {
        openDb();
        cv = new ContentValues();
        cv.put(MyConstants.ACTOR_NAME, actor.getName());
        db.insert(MyConstants.TABLE_NAME_ACTOR, null, cv);
        closeDb();
    }

    public List<Country> getCountriesFromDb() {
        openDb();
        List<Country> tempListCountries = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_COUNTRY, null, null,
                null, null, null, null);

        if (cursor != null) {
            int id;
            while (cursor.moveToNext()) {
                id = cursor.getInt(0);
                String name = cursor.getString(1);
                Country country = new Country(id, name);
                tempListCountries.add(country);
            }
        }
        closeDb();
        return tempListCountries;
    }


    public List<Genre> getGenresFromDb() {
        openDb();
        List<Genre> tempListGenre = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME_GENRE, null, null,
                null, null, null, null);

        if (cursor != null) {
            int id = 0;
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                Genre genre = new Genre(id, name);
                tempListGenre.add(genre);
            }
        }
        closeDb();
        return tempListGenre;
    }

    public List<Actor> getActorsFromDb() {
        openDb();
        List<Actor> tempListActors = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME_ACTOR, null, null,
                null, null, null, null);

        if (cursor != null) {
            int id = 0;
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                Actor actor = new Actor(id, name);
                tempListActors.add(actor);
            }
        }
        closeDb();
        return tempListActors;
    }

    public List<Movie> getMoviesFromDb() {
        openDb();
        List<Movie> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME_MOVIE, null, null,
                null, null, null, null);
        if (cursor != null) {
            int id;
            while (cursor.moveToNext()) {
                id = cursor.getInt(0) - 1;
                String title = cursor.getString(1);
                String year = cursor.getString(2);
                String picUrl = cursor.getString(3);
                String description = cursor.getString(4);
                String genre = getGenresFromDb().get(id).getName();
                String country = getCountriesFromDb().get(id).getName();
                String actor = getActorsFromDb().get(id).getName();
                Movie movie = new Movie(id, title, year, picUrl, description, country,
                        genre, actor);
                tempList.add(movie);
                //Toast.makeText(context, Integer.toString(id) + "GETMDB", Toast.LENGTH_SHORT).show();
            }
        }
        cursor.close();
        closeDb();
        return tempList;
    }

    public void closeDb() {
        myDbHelper.close();
    }
}
