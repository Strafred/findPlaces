package org.strafred;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class AsyncDownloads {
    public static void downloadWeather(String name, double lng, double lat) {
        CompletableFuture.supplyAsync(() -> Requests.createOWRequestURL(name, lng, lat)).thenApply((openWeatherURL) -> {
            try {
                return Requests.requestOWTemperature(openWeatherURL);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }).thenAccept((temperature) -> System.out.println("Temperature in area is: " + temperature.temp + "K"));
    }

    public static void downloadInterestingZones(String name, double lng, double lat) {
        CompletableFuture.supplyAsync(() -> Requests.createOTMRequestURL(name, lng, lat)).thenApply((requestURL) -> {
            try {
                return Requests.requestOTMInterestingZones(requestURL);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }).thenAccept((zones) -> {
            for (int i = 0; i < zones.size(); i++) {
                int current = i;
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return Requests.requestOTMDescription(zones.get(current));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).thenAccept((otmXidInfo) -> {
                    if (otmXidInfo.wikipedia != null) {
                        System.out.println(otmXidInfo.name + ": " + otmXidInfo.wikipedia);
                    }
                });
            }
        });
    }
}