package com.project.m4u.db;


public final class MyConstants {
  public static final String DB_NAME ="M4UDB";
  public static final int DB_VERSION = 15;

  public static final String DESCRIPTION = "description";
  public static final String TABLE_NAME_MOVIE = "my_table_movie";
  public static final String _ID = "_id";
  public static final String TITLE = "title";
  public static final String YEAR = "year";
  public static final String PICURL = "picUrl";

  public static final String TABLE_MOVIE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_MOVIE +
          " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
          TITLE + " TEXT UNIQUE, " +
          YEAR + " TEXT UNIQUE, " +
          PICURL + " TEXT UNIQUE, " +
          DESCRIPTION + " TEXT UNIQUE );";


  public static final String TABLE_NAME_COUNTRY = "my_table_countries";
  public static final String COUNTRY_NAME = "title_country";
  public static final String COUNTRY_ID = "_id";

  public static final String TABLE_COUNTRY_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_COUNTRY +
          " (" + COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
          COUNTRY_NAME + " TEXT );";


  public static final String TABLE_NAME_GENRE = "my_table_genre";
  public static final String GENRE_NAME = "title_genre";

  public static final String TABLE_GENRE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_GENRE +
          " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
          GENRE_NAME + " TEXT );";


  public static final String TABLE_NAME_ACTOR = "my_table_countries";
  public static final String ACTOR_NAME = "title_actor";

  public static final String TABLE_ACTOR_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ACTOR +
          " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
          ACTOR_NAME + " TEXT );";


  public static final String DROP_TABLE_MOVIE = "DROP TABLE IF EXISTS" + TABLE_NAME_MOVIE;
  public static final String DROP_TABLE_COUNTRY = "DROP TABLE IF EXISTS" + TABLE_NAME_COUNTRY;
  public static final String DROP_TABLE_GENRE = "DROP TABLE IF EXISTS" + TABLE_NAME_GENRE;
  public static final String DROP_TABLE_ACTOR = "DROP TABLE IF EXISTS" + TABLE_NAME_ACTOR;
}


