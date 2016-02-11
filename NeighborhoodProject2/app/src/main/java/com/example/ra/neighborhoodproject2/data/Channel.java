package com.example.ra.neighborhoodproject2.data;

import org.json.JSONObject;

/**
 * Created by Ra on 2/11/16.
 */
public class Channel implements JSONPopulator {
    private Item item;
    private Units units;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populate(JSONObject data) {
        units= new Units();
        units.populate(data.optJSONObject("units"));

        item=new Item();
        item.populate(data.optJSONObject("item"));

    }
}
