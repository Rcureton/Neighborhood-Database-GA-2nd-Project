package com.example.ra.neighborhoodproject2.data;

import org.json.JSONObject;

/**
 * Created by Ra on 2/11/16.
 */
public class Item implements JSONPopulator {
    private Conditions conditions;

    public Conditions getConditions() {
        return conditions;
    }

    @Override
    public void populate(JSONObject data) {
        conditions= new Conditions();
        conditions.populate(data.optJSONObject("condition"));


    }
}
