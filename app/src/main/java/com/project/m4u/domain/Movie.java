package com.project.m4u.domain;

public class Movie {

   private int id;

   private String name;

   private String year;

   private String picUrl;

   private String description;

   private String country;

   private String genre;

   private String actor;

    public Movie(int id, String name, String year, String picUrl, String description, String country, String genre, String actor) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.picUrl = picUrl;
        this.description = description;
        this.country = country;
        this.genre = genre;
        this.actor = actor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public String getActor() {
        return actor;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", countryList=" + country +
                ", genreList=" + genre +
                ", actorList=" + actor +
                '}';
    }

}
