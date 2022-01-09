package org.strafred;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMWikiExtracts {
    String title;
    String text;
    String html;
}