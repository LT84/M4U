package com.project.m4u.domain.mapper;

import com.project.m4u.domain.Movie;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieMapper {

    public Movie movieFromJsonArray(JSONObject jsonObject, int countryId, int genreId, int actorId) {

        Movie movie = null;

        try {
            movie = new Movie(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("year"),
                    jsonObject.getString("picUrl"),
                    jsonObject.getString("description"),
                    countryId,
                    genreId,
                    actorId
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;
    }
}
