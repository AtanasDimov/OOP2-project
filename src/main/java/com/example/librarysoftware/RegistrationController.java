package com.example.librarysoftware;

import SqlFilter.SqlFilter;
import Utils.AccountHelper;
import Utils.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class RegistrationController {

    @FXML
    private TextField CreateReader_FirstName;
    @FXML
    private TextField CreateReader_LastName;
    @FXML
    private TextField CreateReader_Username;
    @FXML
    private PasswordField CreateReader_Password;



    public void Submit(){
        String firstName = CreateReader_FirstName.getText();
        String lastName  = CreateReader_LastName.getText();
        String username  = CreateReader_Username.getText();
        String password  = CreateReader_Password.getText();

        if(!SqlFilter.Validate(firstName) || !SqlFilter.Validate(lastName) || !SqlFilter.Validate(username)
                || !SqlFilter.Validate(password)){
            //sql injection detected
            return;
        }

        AccountHelper.RegisterReaderForm(firstName,lastName,username,password);

    }
    public void Clear(){
        CreateReader_FirstName.clear();
        CreateReader_LastName.clear();
        CreateReader_Username.clear();
        CreateReader_Password.clear();
    }
    public void Login(ActionEvent e){
        GUIUtils.changeScene(e,"/Login.fxml","Вписване");
    }

}
