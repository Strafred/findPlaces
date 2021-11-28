package org.strafred;

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