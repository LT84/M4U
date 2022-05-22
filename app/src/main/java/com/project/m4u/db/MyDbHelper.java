package com.project.m4u.db;

import static com.project.m4u.db.MyConstants.TABLE_ACTOR_STRUCTURE;
import static com.project.m4u.db.MyConstants.TABLE_COUNTRY_STRUCTURE;
import static com.project.m4u.db.MyConstants.TABLE_GENRE_STRUCTURE;
import static com.project.m4u.db.MyConstants.TABLE_MOVIE_STRUCTURE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_GENRE_STRUCTURE);
        db.execSQL(TABLE_COUNTRY_STRUCTURE);
        db.execSQL(TABLE_ACTOR_STRUCTURE);
        db.execSQL(TABLE_MOVIE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
