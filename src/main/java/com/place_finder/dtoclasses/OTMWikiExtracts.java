package com.place_finder.dtoclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMWikiExtracts {
    public String title;
    public String text;
    public String html;
}