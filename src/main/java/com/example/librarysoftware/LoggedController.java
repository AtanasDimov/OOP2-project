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

public class LoggedController implements Initializable {
    @FXML
    private Button AdminPanel_btnLogout;
    @FXML
    private Button AdminPanel_btnAdd;
    @FXML
    private Button AdminPanel_btnRemove;
    @FXML
    private TableView AdminPanel_tableview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AdminPanel_btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GUIUtils.changeScene(event,"/Login.fxml","Log in",null,null);
            }

        });


    }
}
