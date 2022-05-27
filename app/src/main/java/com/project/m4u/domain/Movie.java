package com.project.m4u.domain;

public class Movie {

    private int id;

    private String name;

    private String year;

    private String picUrl;

    private String description;

    private int countryId;

    private int genreId;

    private int actorId;

    public Movie(int id, String name, String year, String picUrl, String description, int country, int genre, int actor) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.picUrl = picUrl;
        this.description = description;
        this.countryId = country;
        this.genreId = genre;
        this.actorId = actor;
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

    public int getCountryId() {
        return countryId;
    }

    public int getGenreId() {
        return genreId;
    }

    public int getActorId() {
        return actorId;
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
                ", countryList=" + countryId +
                ", genreList=" + genreId +
                ", actorList=" + actorId +
                '}';
    }
}
