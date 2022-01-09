package com.place_finder.dtoclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMInterestingZone {
    public String type;
    public String id;
    public OTMGeometry geometry;
    public OTMProperties properties;

    @Override
    public String toString() {
        return "OTMInterestingZone{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}