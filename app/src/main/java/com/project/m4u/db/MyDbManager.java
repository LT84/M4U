package com.project.m4u.db;

import static com.project.m4u.db.MyConstants.ACTOR_ID_MOVIE;
import static com.project.m4u.db.MyConstants.ACTOR_NAME;
import static com.project.m4u.db.MyConstants.COUNTRY_ID_MOVIE;
import static com.project.m4u.db.MyConstants.COUNTRY_NAME;
import static com.project.m4u.db.MyConstants.DESCRIPTION;
import static com.project.m4u.db.MyConstants.GENRE_ID_MOVIE;
import static com.project.m4u.db.MyConstants.PIC_URL;
import static com.project.m4u.db.MyConstants.TABLE_NAME_ACTOR;
import static com.project.m4u.db.MyConstants.TABLE_NAME_COUNTRY;
import static com.project.m4u.db.MyConstants.TABLE_NAME_GENRE;
import static com.project.m4u.db.MyConstants.TABLE_NAME_MOVIE;
import static com.project.m4u.db.MyConstants.TITLE;
import static com.project.m4u.db.MyConstants.YEAR;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.m4u.domain.Actor;
import com.project.m4u.domain.Country;
import com.project.m4u.domain.Genre;
import com.project.m4u.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {

    ContentValues cv;
    private Context context;
    public static long result;
    private SQLiteDatabase db;
    private MyDbHelper myDbHelper;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }


    public void openDb() {
        db = myDbHelper.getReadableDatabase();
    }

    public void closeDb() {
        myDbHelper.close();
    }


    //Insert, update, get for movies
    public void insertMovieToDb(Movie movie) {
        openDb();
        cv = new ContentValues();
        cv.put(TITLE, movie.getName());
        cv.put(YEAR, movie.getYear());
        cv.put(PIC_URL, movie.getPicUrl());
        cv.put(DESCRIPTION, movie.getDescription());
        cv.put(COUNTRY_ID_MOVIE, movie.getCountryId());
        cv.put(GENRE_ID_MOVIE, movie.getGenreId());
        cv.put(ACTOR_ID_MOVIE, movie.getActorId());
        db.insert(TABLE_NAME_MOVIE, null, cv);
        closeDb();
    }

    public void updateMovieDb(Movie movie) {
        openDb();
        cv = new ContentValues();
        cv.put(TITLE, movie.getName());
        cv.put(YEAR, movie.getYear());
        cv.put(PIC_URL, movie.getPicUrl());
        cv.put(DESCRIPTION, movie.getDescription());
        cv.put(COUNTRY_ID_MOVIE, movie.getCountryId());
        cv.put(GENRE_ID_MOVIE, movie.getGenreId());
        cv.put(ACTOR_ID_MOVIE, movie.getActorId());
        int id = movie.getId();

        result = db.update(TABLE_NAME_MOVIE, cv, "_id=" + id, null);
        if (result == -1) {
            Log.i("updateMovieDb", "UpdateError");
        } else {
            Log.i("updateMovieDb", "Updated");
        }
        closeDb();
    }

    public List<Movie> getMoviesFromDb() {
        openDb();
        List<Movie> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME_MOVIE, null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            // do what you need with the cursor here
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String year = cursor.getString(2);
            String picUrl = cursor.getString(3);
            String description = cursor.getString(4);
            int countryId = cursor.getInt(5);
            int genreId = cursor.getInt(6);
            int actorId = cursor.getInt(7);
            Movie movie = new Movie(id, title, year, picUrl, description, countryId,
                    genreId, actorId);
            tempList.add(movie);
        }
        cursor.close();
        closeDb();
        return tempList;
    }

    //Insert, update, get for countries
    public void insertCountryDb(Country country) {
        openDb();
        cv = new ContentValues();
        cv.put(COUNTRY_NAME, country.getName());
        db.insert(TABLE_NAME_COUNTRY, null, cv);
        closeDb();
    }

    public void updateCountryDb(Country country) {
        openDb();
        cv = new ContentValues();
        cv.put(COUNTRY_NAME, country.getName());
        int id = country.getId();
        db.update(TABLE_NAME_COUNTRY, cv, "_id_country=" + id, null);

        result = db.update(TABLE_NAME_COUNTRY, cv, "_id_country=" + id, null);
        if (result == -1) {
            Log.i("updateCountryDb", "UpdateError");
        } else {
            Log.i("updateCountryDb", "Updated");
        }
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
        cursor.close();
        closeDb();
        return tempListCountries;
    }

    //Insert, update, get for genres
    public void insertGenreDb(Genre genre) {
        openDb();
        cv = new ContentValues();
        cv.put(MyConstants.GENRE_NAME, genre.getName());
        db.insert(TABLE_NAME_GENRE, null, cv);
        closeDb();
    }

    public void updateGenreDb(Genre genre) {
        openDb();
        cv = new ContentValues();
        cv.put(MyConstants.GENRE_NAME, genre.getName());
        int id = genre.getId();
        db.update(TABLE_NAME_GENRE, cv, "_id_genre=" + id, null);

        result = db.update(TABLE_NAME_GENRE, cv, "_id_genre=" + id, null);
        if (result == -1) {
            Log.i("updateGenreDb", "UpdateError");
        } else {
            Log.i("updateGenreDb", "Updated");
        }
        closeDb();
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
        cursor.close();
        closeDb();
        return tempListGenre;
    }

    //Insert, update, get for actors
    public void insertActorDb(Actor actor) {
        openDb();
        cv = new ContentValues();
        cv.put(MyConstants.ACTOR_NAME, actor.getName());
        db.insert(MyConstants.TABLE_NAME_ACTOR, null, cv);
        closeDb();
    }

    public void updateActorDb(Actor actor) {
        openDb();
        cv = new ContentValues();
        cv.put(ACTOR_NAME, actor.getName());
        int id = actor.getId();
        db.update(TABLE_NAME_ACTOR, cv, "_id_actor=" + id, null);

        result = db.update(TABLE_NAME_ACTOR, cv, "_id_actor=" + id, null);
        if (result == -1) {
            Log.i("updateActorDb", "UpdateError");
        } else {
            Log.i("updateActorDb", "Updated");
        }
        closeDb();
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
        cursor.close();
        closeDb();
        return tempListActors;
    }
}
