package com.example.ra.neighborhoodproject2;

/**
 * Created by Ra on 2/2/16.
 */
public class Neighborhood {

    public int id;
    public String name;
    public String neighborhood;
    public String address;
    public String description;
    public String favorite;
    public String url;

    public Neighborhood(int id, String name, String neighborhood, String address, String description, String favorite, String url) {
        this.id = id;
        this.name= name;
        this.neighborhood = neighborhood;
        this.address = address;
        this.description = description;
        this.favorite= favorite;
        this.url=url;
    }
}

