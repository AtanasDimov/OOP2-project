package com.example.librarysoftware;

import ExceptionHandling.LibraryException;
import ExceptionHandling.MissingReservationsException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Alert.Alert;
import Library.Dto.java.Alert.AlertFactory;
import Library.Dto.java.Alert.AlertSeverity;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Logger.Logger;
import Utils.ItemHelper;
import Utils.QueryGenerator;
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

    public static void AddReservation(Reservation res){
        reservations.add(res);
    }

    public static void DeleteReservation(int id){
        for(Reservation res : reservations){
            if(res.getId() == id){
                reservations.remove(res);
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
            Reservation reservation = reservations.stream().filter(res -> id == res.getId()).findFirst().orElse(null);
            int readerId = reservation.getReaderId();

            LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();

            String firstName = (String)repository.GetObject(QueryGenerator.GetReaderFirstNameById(readerId));
            String lastName = (String)repository.GetObject(QueryGenerator.GetReaderLastNameById(readerId));

            String message = "Reader" + firstName + " " + lastName + " has reservation " + id + " overdue!";

            Alert alert = AlertFactory.CreateAlert(message, AlertSeverity.OverDue);
            repository.AddObject(alert);

            repository.CloseSession();
        }
    }
}
