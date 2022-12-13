package com.example.librarysoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CreateItem.fxml"));
        primaryStage.setTitle("УЙ");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}