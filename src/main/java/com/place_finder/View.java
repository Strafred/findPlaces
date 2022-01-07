package com.place_finder;

import com.place_finder.dtoclasses.GHPlace;
import com.place_finder.dtoclasses.OTMInterestingZone;
import com.place_finder.dtoclasses.OTMXidInfo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View {
    public static VBox setScene(Stage primaryStage) {
        primaryStage.setTitle("Find Places Window Application");
        VBox root = new VBox();

        primaryStage.setScene(new Scene(root, 400, 650));
        primaryStage.show();
        return root;
    }

    public static void drawPlaceInputField(VBox root) {
        TextField textField = new TextField();
        textField.setPrefColumnCount(11);
        Button getPlacesButton = new Button("Get Places");

        root.getChildren().add(textField);
        root.getChildren().add(getPlacesButton);

        Controller.activateGetPlacesButton(getPlacesButton, root, textField);
    }

    public static void drawClearButton(VBox root, Stage primaryStage) {
        Button clearButton = new Button("Clear ALL");
        root.getChildren().add(clearButton);

        Controller.activateClearButton(clearButton, root, primaryStage);
    }

    public static void drawPlacesList(List<GHPlace> places, VBox root) {
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < places.size(); i++) {
            items.add(i, i + 1 + ") country: " + places.get(i).country + ", name: " + places.get(i).name);
        }

        list.setItems(items);
        list.setPrefSize(300, 80);

        root.getChildren().add(list);
    }

    public static void drawZoneInputField(List<GHPlace> places, VBox root) {
        TextField interestingZonesChooseField = new TextField();
        interestingZonesChooseField.setPrefColumnCount(11);
        Button getInterestingZonesButton = new Button("Get Interesting Zones by place index:");

        root.getChildren().add(interestingZonesChooseField);
        root.getChildren().add(getInterestingZonesButton);

        Controller.activateGetZonesButton(getInterestingZonesButton, root, interestingZonesChooseField, places);
    }

    public static void setStage(Stage primaryStage) {
        var root = View.setScene(primaryStage);

        View.drawClearButton(root, primaryStage);
        View.drawPlaceInputField(root);
    }

    public static ListView<String> createInterestingZonesList(List<OTMInterestingZone> interestingZones) {
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < interestingZones.size(); i++) {
            items.add(i, i + 1 + ") place: " + interestingZones.get(i).properties.name);
        }

        list.setItems(items);
        list.setPrefSize(300, 80);

        return list;
    }

    public static Button createDescriptionRequestButton() {
        return new Button("Get Description by zone index:");
    }

    public static TextField createDescriptionRequestField() {
        TextField descriptionChooseField = new TextField();
        descriptionChooseField.setPrefColumnCount(11);

        return descriptionChooseField;
    }

    public static void createZoneDescription(List<OTMInterestingZone> interestingZones, int elementIndex, VBox root) throws IOException, InterruptedException {
        OTMXidInfo otmXidInfo = Requests.requestOTMDescription(interestingZones.get(elementIndex));

        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add(otmXidInfo.wikipedia_extracts.text);

        list.setItems(items);
        list.setPrefSize(300, 50);

        Platform.runLater(() -> root.getChildren().add(list));
    }

    public static List<Node> createInterestingDescriptions(ListView<String> interestingZonesList, TextField descriptionRequestField, Button descriptionRequestButton) {
        List<Node> elementsToDraw = new ArrayList<>();
        elementsToDraw.add(interestingZonesList);
        elementsToDraw.add(descriptionRequestField);
        elementsToDraw.add(descriptionRequestButton);
        return elementsToDraw;
    }

    public static void drawInterestingDescriptions(List<Node> elements, VBox root) {
        elements.forEach(elem -> root.getChildren().add(elem));
    }
}