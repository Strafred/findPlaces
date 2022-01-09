package com.place_finder.dtoclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMInfo {
    public String type;
    public List<OTMInterestingZone> features;
}