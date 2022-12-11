package com.example.librarysoftware;

import Library.Dto.java.DTOLibraryItems.Reservation;
import Utils.ReservationHelper;

import java.util.List;

public class ReservationSession {
    private static List<Reservation> reservations;

    public static List<Reservation> GetInstance(){
        if(reservations == null){
            LoadReservations();
        }
        return reservations;
    }

    public static void LoadReservations(){
        try{
            ReservationHelper.LoadReservations();
        }
        catch(Exception ex){

        }
    }
}
