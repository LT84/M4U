package com.project.m4u.domain.mapper;

import com.project.m4u.domain.Movie;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieMapper {

    public Movie movieFromJsonArray(JSONObject jsonObject) {

        Movie movie = null;

        try {
            movie = new Movie(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("year"),
                    jsonObject.getString("picUrl"),
                    jsonObject.getString("description"),
                    null,
                    null,
                    null
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;
    }
}
