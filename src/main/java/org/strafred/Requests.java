package org.strafred;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Requests {
    static final String GRAPH_HOPPER_URL =  "https://graphhopper.com/api/1/geocode?";
    static final String GH_KEY = "key=735465ac-be41-408d-b1f5-ef8beb545c96";
    static final String OPEN_TRIP_MAP_URL = "http://api.opentripmap.com/0.1/ru/places/bbox?";
    static final String OTM_KEY = "5ae2e3f221c38a28845f05b697f3830d42a0f72a15c66f468a2980f4";
    static final String OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/weather?";
    static final String OW_KEY = "ecea7d298982521bbc9202bb1ae36875";

    public static String createGHRequestURL(BufferedReader input) throws IOException {
        String place = "q=" + input.readLine().replaceAll(" ", "%20");
        String locale = "locale=en";
        String debug = "debug=true";

        return GRAPH_HOPPER_URL + "&" + place + "&" + locale + "&" + debug + "&" + GH_KEY;
    }

    public static List<GHPlace> requestGHPlaces(String requestURL) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestURL))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        GHInfo info = mapper.readValue(response.body(), new TypeReference<>() {});
        return info.hits;
    }

    public static String createOTMRequestURL(String name, double lng, double lat) {
        String lon_min = Double.toString(lng - 0.1);
        String lat_min = Double.toString(lat - 0.1);
        String lon_max = Double.toString(lng + 0.1);
        String lat_max = Double.toString(lat + 0.1);

        return OPEN_TRIP_MAP_URL + "lon_min=" + lon_min + "&" + "lat_min=" + lat_min + "&" + "lon_max=" + lon_max
        + "&" + "lat_max=" + lat_max + "&" + "kinds=interesting_places&format=geojson&apikey=" + OTM_KEY;
    }

    public static List<OTMInterestingZone> requestOTMInterestingZones(String requestURL) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestURL))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        OTMInfo info = mapper.readValue(response.body(), new TypeReference<>() {});

        return info.features;
    }

    public static OTMXidInfo requestOTMDescription(OTMInterestingZone zone) throws IOException, InterruptedException {
        String xidOpenTripMapURL = "http://api.opentripmap.com/0.1/ru/places/xid/" + zone.properties.xid
                + "?apikey=" + OTM_KEY;

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(xidOpenTripMapURL))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<>() {});
    }

    public static String createOWRequestURL(String name, double lng, double lat) {
        return OPEN_WEATHER_MAP_URL + "lat=" + lat + "&" + "lon=" + lng + "&" + "appid=" + OW_KEY;
    }

    public static OWTemp requestOWTemperature(String openWeatherURL) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(openWeatherURL))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        OWInfo info = mapper.readValue(response.body(), new TypeReference<>() {});
        return info.main;
    }
}