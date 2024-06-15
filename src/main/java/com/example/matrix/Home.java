package com.example.matrix;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {

    @FXML
    Button id2x2, id3x3, id4x4;

    public void on2x2Clicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("2x2Matrix.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) id2x2.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
    public void on3x3Clicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Matrix.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) id3x3.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
    public void on4x4Clicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("4x4Matrix.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) id4x4.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
}
