package com.example.librarysoftware;

import Utils.AccountHelper;
import Utils.GUIUtils;
import Utils.LibraryDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterOperatorController {

    @FXML
    private TextField RegOperator_OperatorUsername;
    @FXML
    private PasswordField RegOperator_OperatorPassword;

    public void RegisterClear(){
        RegOperator_OperatorUsername.clear();
        RegOperator_OperatorPassword.clear();
    }
    public void RegisterOperator(){
        String username = RegOperator_OperatorUsername.getText();
        String password = RegOperator_OperatorPassword.getText();
        AccountHelper.RegisterOperator(username,password);
    }
    public void RegisterBack(ActionEvent event){
        GUIUtils.changeScene(event,"/RegisterOperator.fxml", LibraryDictionary.AdminPanelTitle);
    }
}
