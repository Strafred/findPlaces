package com.place_finder;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class AsyncDownloads {
    public static void downloadWeather(String name, double lng, double lat, VBox root) {
        CompletableFuture.supplyAsync(() -> Requests.createOWRequestURL(name, lng, lat)).thenApply((openWeatherURL) -> {
            try {
                return Requests.requestOWTemperature(openWeatherURL);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;

        }).thenAccept(temp -> Platform.runLater(() ->
                root.getChildren().add(new Label("Temperature in area is: " + temp.temp + "K"))));
    }

    public static void downloadInterestingZones(String name, double lng, double lat, VBox root) {
        CompletableFuture.supplyAsync(() -> Requests.createOTMRequestURL(name, lng, lat)).thenApply((requestURL) -> {
            try {
                return Requests.requestOTMInterestingZones(requestURL);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;

        }).thenApply((interestingZones) -> {
            var interestingZonesList = View.createInterestingZonesList(interestingZones);
            var descriptionRequestButton = View.createDescriptionRequestButton();
            var descriptionRequestField = View.createDescriptionRequestField();

            Controller.activateDescriptionRequestButton(interestingZones, descriptionRequestField,
                    descriptionRequestButton, root);

            return View.createInterestingDescriptions(interestingZonesList, descriptionRequestField, descriptionRequestButton);
        }).thenAccept(elements -> Platform.runLater(() -> View.drawInterestingDescriptions(elements, root)));
    }
}