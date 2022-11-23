package com.example.librarysoftware;

import Hibernate.Control.Main.HibernateMain;
import Hibernate.Control.Main.LibraryRepository;
import Library.Dto.java.Contracts.AccountsInterface;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.Admin;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPage extends Application {
    @FXML
    private TextField user;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}