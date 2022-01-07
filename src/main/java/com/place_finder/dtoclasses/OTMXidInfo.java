package com.place_finder.dtoclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMXidInfo {
    public String xid;
    public String name;
    public String wikidata;
    public String wikipedia;
    public OTMWikiExtracts wikipedia_extracts;
}