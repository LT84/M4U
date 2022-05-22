package com.project.m4u.domain.mapper;

import com.project.m4u.domain.Country;

import org.json.JSONException;
import org.json.JSONObject;

public class CountryMapper {

    public Country countryFromJsonArray(JSONObject jsonObject) {

        Country country = null;

        try {

            country = new Country (
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return country;
    }
}
