module com.example.barpancho {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.barpancho to javafx.fxml;
    exports com.example.barpancho;
}