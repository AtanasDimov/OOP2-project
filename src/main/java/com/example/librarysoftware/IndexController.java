package com.example.librarysoftware;

import ExceptionHandling.LibraryException;
import ExceptionHandling.NotLoggedException;
import ExceptionHandling.SeverityCodes;
import Logger.Logger;
import Sessions.UserSession;
import Utils.GUIUtils;
import Utils.LibraryDictionary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    @FXML
    private Button Index_btnLogout;
    @FXML
    private Button Index_btnDynamic1;
    @FXML
    private Button Index_btnDynamic2;
    @FXML
    private Button Index_btnDynamic3;
    @FXML
    private Button Index_btnDynamic4;
    @FXML
    private Label Index_WelcomeUserLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Index_btnDynamic1.setVisible(false);
        Index_btnDynamic2.setVisible(false);
        Index_btnDynamic3.setVisible(false);
        Index_btnDynamic4.setVisible(false);

        try {
            String username = UserSession.getInstance().getUsername();
            Index_WelcomeUserLabel.setText("Добре дошъл, "+ username + " !");
        } catch (NotLoggedException e) {
            String errorMessage = "User not logged in";
            Logger log = new Logger();
            LibraryException lEx = new LibraryException(errorMessage, SeverityCodes.Light);
            log.LogException(lEx);

        }


            if (UserSession.isAdmin()) {
                Index_WelcomeUserLabel.setVisible(true);
                Index_btnDynamic1.setVisible(true);
                Index_btnDynamic1.setText("Отвори Панел Оператори");
                Index_btnDynamic1.setOnAction(event -> {
                    GUIUtils.changeScene(event,"/AdminPanel.fxml","Контролен Панел/Оператори");
                });
                Index_btnDynamic2.setVisible(true);
                Index_btnDynamic2.setText("Добавяне на артикул в библиотека");
                Index_btnDynamic2.setOnAction(event -> {
                    GUIUtils.changeScene(event,"/CreateItem.fxml","Добавяне на артикул");
                });
                Index_btnDynamic3.setVisible(true);
                Index_btnDynamic3.setText(LibraryDictionary.LoginAsOperator);
                Index_btnDynamic3.setOnAction(event -> {
                    GUIUtils.changeScene(event,"/OperatorPanel.fxml",LibraryDictionary.OperatorPanel);
                });
                Index_btnDynamic4.setVisible(true);
                Index_btnDynamic4.setText(LibraryDictionary.ReferenceTab);
                Index_btnDynamic4.setOnAction(event -> {
                    GUIUtils.changeScene(event,"/ReferenceTab.fxml",LibraryDictionary.OperatorPanel);
                });

            }
            else if (UserSession.isOperator()) {

            }
            else {
                Index_WelcomeUserLabel.setVisible(true);
                Index_btnDynamic1.setVisible(true);
                Index_btnDynamic1.setText(LibraryDictionary.BorrowItem);
                Index_btnDynamic1.setOnAction(event -> {
                    GUIUtils.changeScene(event,"/BorrowItem.fxml",LibraryDictionary.BorrowItem);
                });
                Index_btnDynamic2.setVisible(true);
                Index_btnDynamic2.setText(LibraryDictionary.ReaderPanelTitle);
                Index_btnDynamic2.setOnAction(event -> {
                    GUIUtils.changeScene(event,"/ReaderPanel.fxml",LibraryDictionary.ReaderPanelTitle);
                });
            }


        Index_btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSession.LogOut();
                GUIUtils.changeScene(event,"/Login.fxml","Вписване");
            }


        });


    }
}
