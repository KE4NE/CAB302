module com.example.assessment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assessment to javafx.fxml;
    exports com.example.assessment;
    exports com.example.assessment.controllers;
    opens com.example.assessment.controllers to javafx.fxml;
    exports com.example.assessment.apps;
    opens com.example.assessment.apps to javafx.fxml;
}