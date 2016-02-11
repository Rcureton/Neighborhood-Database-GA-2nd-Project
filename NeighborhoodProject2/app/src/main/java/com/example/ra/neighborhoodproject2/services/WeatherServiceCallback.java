package com.example.ra.neighborhoodproject2.services;

import com.example.ra.neighborhoodproject2.data.Channel;

/**
 * Created by Ra on 2/11/16.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}
