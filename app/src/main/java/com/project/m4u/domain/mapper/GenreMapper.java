package com.project.m4u.domain.mapper;

import com.project.m4u.domain.Genre;

import org.json.JSONException;
import org.json.JSONObject;

public class GenreMapper {

    public Genre genreFromJsonArray(JSONObject jsonObject) {

        Genre genre = null;

        try {

            genre = new Genre (
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return genre;
    }
}
