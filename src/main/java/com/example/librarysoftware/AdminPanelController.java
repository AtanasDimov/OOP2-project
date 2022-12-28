package com.example.librarysoftware;

import Library.Dto.java.DTOAccount.OperatorAccount;
import Utils.GUIUtils;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminPanelController implements Initializable {
    @FXML
    private Button AdminPanel_btnRemove;
    @FXML
    private Button AdminPanel_btnAdd;
    @FXML
    private Button AdminPanel_btnBack;
    @FXML
    private Button AdminPanel_btnOpenAlerts;
    @FXML
    private TableView<OperatorAccount> AdminPanel_TableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdminPanel_btnOpenAlerts.setOnAction(event -> {
            GUIUtils.changeScene(event,"/AdminAlerts.fxml","Контролен панел/известия");
        });
        AdminPanel_btnBack.setOnAction(event -> {GUIUtils.changeScene(event,"/Index.fxml","Индекс");});
    }

}
