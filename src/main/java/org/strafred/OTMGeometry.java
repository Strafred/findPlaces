package org.strafred;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTMGeometry {
    public String type;
    public List<Double> coordinates;

    @Override
    public String toString() {
        return "OTMGeometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}