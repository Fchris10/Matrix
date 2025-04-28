package com.example.matrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Image logo = new Image("file:///C:/Users/chris/IdeaProjects/Matrix/src/main/resources/com/example/matrix/iconsAndImages/matrix_logo.jpg");
        stage.getIcons().add(logo);

        stage.setTitle("Matrix solver");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}