package com.example.librarysoftware;

import ExceptionHandling.AlreadyLoggedException;
import ExceptionHandling.LibraryException;
import ExceptionHandling.NotExistException;
import ExceptionHandling.SeverityCodes;

import Logger.Logger;
import Utils.AccountHelper;

import Utils.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class LoginController {
    @FXML
    private TextField Login_Username;
    @FXML
    private PasswordField Login_Password;
    @FXML
    private Label Login_ErrorLabel;

    public void Submit(ActionEvent e) {
        String username = Login_Username.getText();
        String password = Login_Password.getText();

        try{
            AccountHelper.LogInUser(username, password);
            GUIUtils.changeScene(e,"Index.fxml","Добре дошли",null,null);
        }
        catch(Exception ex){
            Logger log = new Logger();
            if(ex instanceof NotExistException){
                log.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Light));
            }
            if(ex instanceof AlreadyLoggedException){
                log.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Medium));
            }
        }

    }

    public void Clear() {
        Login_ErrorLabel.setText("");
        Login_Password.clear();
        Login_Username.clear();

    }

    public void Register(ActionEvent e){
        GUIUtils.changeScene(e, "/Register.fxml","Регистрация на читател:",null,null);
    }

}
