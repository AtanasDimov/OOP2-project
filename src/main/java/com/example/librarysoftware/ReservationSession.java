package com.example.librarysoftware;

import ExceptionHandling.LibraryException;
import ExceptionHandling.MissingReservationsException;
import ExceptionHandling.SeverityCodes;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Logger.Logger;
import Utils.ReservationHelper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReservationSession {
    private static List<Reservation> reservations;
    private static Timer timer = new Timer();

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

    public static void OnStartUp(){
        if(reservations == null){
            LoadReservations();
        }
        for(Reservation res : reservations){
            timer.schedule(new ReservationOverdue(), res.getDueDate());
        }
    }

    public static class ReservationOverdue extends TimerTask {
        @Override
        public void run() {

        }
    }
}
