package org.strafred;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GHPlace {
    public long osm_id;
    public String osm_type;
    public String country;
    public String osm_key;
    public String city;
    public String osm_value;
    public String postcode;
    public String name;
    public GHPlaceCord point;

    @Override
    public String toString() {
        return "GHPlace{" +
                "osm_id=" + osm_id +
                ", osm_type='" + osm_type + '\'' +
                ", country='" + country + '\'' +
                ", osm_key='" + osm_key + '\'' +
                ", city='" + city + '\'' +
                ", osm_value='" + osm_value + '\'' +
                ", postcode='" + postcode + '\'' +
                ", name='" + name + '\'' +
                ", point=" + point +
                '}';
    }
}