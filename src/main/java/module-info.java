module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires com.jfoenix;
    requires java.sql;


    opens org.example to javafx.fxml,javafx.graphics;
    exports org.example;

    opens org.example.controller to javafx.controls,javafx.fxml,javafx.graphics;
    exports org.example.controller;
}