module com.example.matrix {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.matrix to javafx.fxml;
    exports com.example.matrix;
}