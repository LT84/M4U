package com.project.m4u.domain.mapper;

import com.project.m4u.domain.Actor;

import org.json.JSONException;
import org.json.JSONObject;

public class ActorMapper {

    public Actor actorFromJsonArray(JSONObject jsonObject) {

        Actor actor = null;

        try {

            actor = new Actor(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return actor;
    }

    public Actor actorFromMovieJsonArray(JSONObject jsonObject) {

        Actor actor = null;
        try {

            actor = new Actor(
                    jsonObject.getJSONObject("actorDto").getInt("id"),
                    jsonObject.getJSONObject("actorDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return actor;
    }
}
