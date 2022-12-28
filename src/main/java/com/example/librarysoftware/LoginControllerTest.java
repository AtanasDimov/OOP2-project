package com.example.librarysoftware;


import Hibernate.Control.Main.Repository.LibraryRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {
    @Mock
    private TextField Login_Username;

    @Mock
    private PasswordField Login_Password;

    @Mock
    private ActionEvent e;

    @InjectMocks
    private LoginController login;

    @Test
    void submit() {
        Login_Username.setText("admin");
        Login_Password.setText("admin");
        login.Submit(e);
    }
}