package com.example.librarysoftware;

import ExceptionHandling.NotLoggedException;
import Logger.Logger;
import Utils.GUIUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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
    private Button Index_btnDynamic5;
    @FXML
    private Label Index_WelcomeUserLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Index_btnDynamic1.setVisible(false);
        Index_btnDynamic1.setAlignment(Pos.CENTER);
        Index_btnDynamic2.setVisible(false);
        Index_btnDynamic2.setAlignment(Pos.CENTER);
        Index_btnDynamic3.setVisible(false);
        Index_btnDynamic3.setAlignment(Pos.CENTER);
        Index_btnDynamic4.setVisible(false);
        Index_btnDynamic4.setAlignment(Pos.CENTER);
        Index_btnDynamic5.setVisible(false);
        Index_btnDynamic5.setAlignment(Pos.CENTER);
        try {
            String username = UserSession.getInstance().getUsername();
            Index_WelcomeUserLabel.setText("Добре дошъл, "+ username + " !");
        } catch (NotLoggedException e) {
            throw new RuntimeException(e);

        }


            if (UserSession.isAdmin()) {
                Index_WelcomeUserLabel.setVisible(true);
                Index_btnDynamic1.setVisible(true);
                Index_btnDynamic1.setText("Open Admin Panel");
                Index_btnDynamic1.setOnAction(event -> {
                    GUIUtils.changeScene(event,"/AdminPanel.fxml","Контролен Панел/Оператори");
                });

                Index_btnDynamic5.setVisible(true);

            } else if (UserSession.isOperator()) {

            } else {

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
