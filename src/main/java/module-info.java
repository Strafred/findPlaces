module com.place_finder {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;


    opens com.place_finder to javafx.fxml;
    exports com.place_finder;
    exports com.place_finder.dtoclasses;
}