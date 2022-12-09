package com.example.librarysoftware;

import ExceptionHandling.NotLoggedException;
import Library.Dto.java.DTOAccount.AccountBase;

public class UserSession {
    private static AccountBase user;


    public AccountBase getInstance() throws NotLoggedException{
        if(user == null)
            throw new NotLoggedException("User not logged in!");
        return this.user;
    }

    public boolean logIn(AccountBase user){
        this.user = user;

        return true;
    }
}
