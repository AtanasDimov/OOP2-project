package com.example.librarysoftware;

import Utils.GUIUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    @FXML
    private Button Index_btnLogout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(UserSession.isAdmin()){

        }


        Index_btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GUIUtils.changeScene(event,"/Login.fxml","Вписване",null,null);
            }

        });


    }
}
