module com.example.design {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.design to javafx.fxml;
    exports com.example.design;
}