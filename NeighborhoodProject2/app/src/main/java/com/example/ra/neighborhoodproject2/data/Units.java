package com.example.ra.neighborhoodproject2.data;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Ra on 2/11/16.
 */
public class Units implements JSONPopulator {
    private String temperature;


    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature= data.optString("temperature");
        Log.d("get temp", "temp"+temperature);


    }
}
