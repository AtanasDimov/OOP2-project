package com.example.librarysoftware;

import ExceptionHandling.LibraryException;
import ExceptionHandling.MissingReservationsException;
import ExceptionHandling.SeverityCodes;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Logger.Logger;
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
            reservations = ReservationHelper.LoadReservations();
        }
        catch(Exception ex){
            if(ex instanceof MissingReservationsException)
            {
                Logger log = new Logger();
                log.LogException(new LibraryException(ex.getMessage(), SeverityCodes.Severe));
            }
        }
    }
}
