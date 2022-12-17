package com.example.librarysoftware;

import Utils.AccountHelper;
import Utils.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrationController {
    @FXML
    private TextField CreateReader_FirstName;
    @FXML
    private TextField CreateReader_LastName;
    @FXML
    private TextField CreateReader_Username;
    @FXML
    private TextField CreateReader_Password;

    @FXML
    private Button CreateReader_Submit;
    @FXML
    private Button CreateReader_Clear;
    @FXML
    private Button CreateReader_btnLogin;

    public void Submit(){
        String firstName = CreateReader_FirstName.getText();
        String lastName  = CreateReader_LastName.getText();
        String username  = CreateReader_Username.getText();
        String password  = CreateReader_Password.getText();

        AccountHelper.RegisterReaderForm(firstName,lastName,username,password);

    }
    public void Clear(){

    }
    public void Login(ActionEvent e){
        GUIUtils.changeScene(e,"/Login.fxml","Вписване");
    }

}
