package com.example.librarysoftware;

import ExceptionHandling.LibraryException;
import ExceptionHandling.MissingReservationsException;
import ExceptionHandling.SeverityCodes;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Logger.Logger;
import Utils.ItemHelper;
import Utils.ReaderHelper;
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

    public static void AddReservation(Reservation res, int itemId){
        reservations.add(res);
        ReservationHelper.AddReservation(res);
        ItemHelper.GiveItem(itemId);
    }

    public static void DeleteReservation(Reservation res, int itemId, int readerId){
        reservations.remove(res);
        ReservationHelper.DeleteReservation(res);
        ItemHelper.ReturnItem(itemId);
        ReaderHelper.ReturnItem(readerId, res.getId());
    }

    public static void DeleteReservation(int id){
        for(Reservation res : reservations){
            if(res.getId() == id){
                reservations.remove(res);
                ReservationHelper.DeleteReservation(res);
            }
        }
    }

    public static void Configure(){
        if(reservations == null){
            LoadReservations();
        }
        for(Reservation res : reservations){
            timer.schedule(new ReservationOverdue(res.getId()), res.getDueDate());
        }
    }

    public static class ReservationOverdue extends TimerTask {
        private final int id;

        ReservationOverdue(int id){
            this.id = id;
        }

        @Override
        public void run() {

        }
    }
}
