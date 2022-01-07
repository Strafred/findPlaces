package com.place_finder.dtoclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GHPlaceCord {
    public double lng;
    public double lat;

    @Override
    public String toString() {
        return "GHPlaceCord{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}