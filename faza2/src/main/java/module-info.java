module com.example.theater {
    requires javafx.controls;
    requires javafx.fxml;


    opens theater.model to javafx.fxml;
    exports theater.model;
}