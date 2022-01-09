package org.strafred;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMInfo {
    public String type;
    public List<OTMInterestingZone> features;
}