module com.example.homework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.homework to javafx.fxml;
    exports com.example.homework;
}