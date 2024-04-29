module com.example.assessment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.common;
    //requires org.junit.jupiter.api;


    opens com.example.assessment to javafx.fxml;
    exports com.example.assessment;
    exports com.example.assessment.controllers;
    opens com.example.assessment.controllers to javafx.fxml;
}