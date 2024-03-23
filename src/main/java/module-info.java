module com.example.assessment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assessment to javafx.fxml;
    exports com.example.assessment;
}