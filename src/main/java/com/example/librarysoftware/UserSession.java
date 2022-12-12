package com.example.librarysoftware;

import ExceptionHandling.NotLoggedException;
import Library.Dto.java.DTOAccount.AccountBase;
import Library.Dto.java.DTOAccount.Admin;

public class UserSession {
    private static AccountBase user;


    public static AccountBase getInstance() throws NotLoggedException{
        if(user == null)
            throw new NotLoggedException("User not logged in!");
        return user;
    }

    public static boolean logIn(AccountBase u){
        user = u;

        return true;
    }

    public static boolean isAdmin(){
        if(user instanceof Admin)
            return true;
        else return false;
    }

    public static boolean isLoggedIn(){
        if(user == null)
            return false;
        else return true;
    }

    public static boolean LogOut(){
        if(user != null)
            user = null;
        return true;
    }
}
