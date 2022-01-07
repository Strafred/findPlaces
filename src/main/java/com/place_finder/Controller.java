package com.place_finder;

import com.place_finder.dtoclasses.GHPlace;
import com.place_finder.dtoclasses.OTMInterestingZone;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Controller extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static void activateGetPlacesButton(Button getPlacesButton, VBox root, TextField textField) {
        getPlacesButton.setOnAction(event -> {
            try {
                String requestURL = Requests.createGHRequestURL(textField.getText());

                var places = Requests.requestGHPlaces(requestURL);

                View.drawPlacesList(places, root);
                View.drawZoneInputField(places, root);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    static void activateClearButton(Button clearButton, VBox root, Stage primaryStage) {
        clearButton.setOnAction(action -> {
            root.getChildren().clear();
            View.setStage(primaryStage);
        });
    }

    public static void activateGetZonesButton(Button getInterestingZonesButton, VBox root,
                                              TextField interestingZonesChooseField, List<GHPlace> places) {

        getInterestingZonesButton.setOnAction(click -> {
            int placeNumber = Integer.parseInt(interestingZonesChooseField.getText());

            AsyncDownloads.downloadWeather(places.get(placeNumber).name,
                    places.get(placeNumber).point.lng, places.get(placeNumber).point.lat, root);

            AsyncDownloads.downloadInterestingZones(places.get(placeNumber).name,
                    places.get(placeNumber).point.lng, places.get(placeNumber).point.lat, root);
        });
    }

    public static void activateDescriptionRequestButton(List<OTMInterestingZone> interestingZones, TextField descriptionRequestField,
                                                        Button descriptionRequestButton, VBox root) {

        descriptionRequestButton.setOnAction((action) -> {
            int elementIndex = Integer.parseInt(descriptionRequestField.getText()) - 1;

            try {
                View.createZoneDescription(interestingZones, elementIndex, root);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        View.setStage(primaryStage);
    }
}