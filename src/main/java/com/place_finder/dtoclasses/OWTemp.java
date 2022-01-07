package com.place_finder.dtoclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OWTemp {
    public double temp;

    @Override
    public String toString() {
        return "OWTemp{" +
                "temp=" + temp +
                '}';
    }
}