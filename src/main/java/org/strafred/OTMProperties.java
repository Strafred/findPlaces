package org.strafred;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMProperties {
    public String xid;
    public String name;
    public long rate;
    public String osm;
    public String kinds;

    @Override
    public String toString() {
        return "OTMProperties{" +
                "xid='" + xid + '\'' +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", osm='" + osm + '\'' +
                ", kinds='" + kinds + '\'' +
                '}';
    }
}