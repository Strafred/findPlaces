package org.strafred;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Write place here:");
        String requestURL = Requests.createGHRequestURL(input);
        List<GHPlace> places = Requests.requestGHPlaces(requestURL);

        for (int i = 0; i < places.size(); i++) {
            System.out.println(i + 1 + ") country: " + places.get(i).country + ", name: " + places.get(i).name);
        }
        System.out.println("Choose place number:");
        int placeNumber = Integer.parseInt(input.readLine()) - 1;

        AsyncDownloads.downloadWeather(places.get(placeNumber).name,
                places.get(placeNumber).point.lng, places.get(placeNumber).point.lat);

        AsyncDownloads.downloadInterestingZones(places.get(placeNumber).name,
                places.get(placeNumber).point.lng, places.get(placeNumber).point.lat);

        sleep(100000);
    }
}