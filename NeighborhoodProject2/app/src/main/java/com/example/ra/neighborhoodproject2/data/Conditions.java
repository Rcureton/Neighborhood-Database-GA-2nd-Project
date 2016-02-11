package com.example.ra.neighborhoodproject2.data;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Ra on 2/11/16.
 */
public class Conditions implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;


    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code=data.optInt("code");
        temperature= data.optInt("temp");
        Log.d("get temp","temp"+temperature);
        description=data.optString("text");


    }
}
