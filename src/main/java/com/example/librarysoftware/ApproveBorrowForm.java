package com.example.librarysoftware;

import Library.Dto.java.DTOLibraryItems.Reservation;
import Library.Dto.java.DTOLibraryItems.ReservationDueDates;
import Library.Dto.java.DTOLibraryItems.ReservationTypes;
import Library.Dto.java.Form.Form;
import Utils.FormHelper;
import Utils.QueryGenerator;
import Utils.ReaderHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApproveBorrowForm extends Application {
    public static class HBoxCell extends HBox {
        Label readerUsername = new Label();
        Label ItemTitle = new Label();
        Button btnApprove = new Button();
        Button btnReject = new Button();
        ComboBox<String> dueDates = new ComboBox<>();
        ComboBox<String> reservationTypes = new ComboBox<>();
        HBoxCell( int readerId, int itemId,LocalDate dueDate,ReservationTypes reservationType) {
            super();
            ObservableList<String> dueDatesList = FXCollections.observableArrayList();
            ObservableList<String> reservationsTypeList = FXCollections.observableArrayList();
            Arrays.stream(ReservationDueDates.values()).forEach(d -> dueDatesList.add(d.toString()));
            Arrays.stream(ReservationTypes.values()).forEach(d -> reservationsTypeList.add(d.toString()));

            dueDates.setItems(dueDatesList);
            dueDates.setPromptText("Период на заемане");
            reservationTypes.setItems(reservationsTypeList);
            reservationTypes.setPromptText("Вид на заемане");

            readerUsername.setText(QueryGenerator.GetReaderById(readerId) + " ");
            ItemTitle.setText(QueryGenerator.GetItemById(itemId) + " ");

            btnApprove.setText("Одобри");
            btnReject.setText("Отхвърли");

            btnApprove.setOnAction(event -> ApproveReservation());
            btnReject.setOnAction(event -> RejectReservation());
            this.getChildren().addAll(readerUsername,ItemTitle,dueDates,reservationTypes,btnApprove,btnReject);


        }
        private void ApproveReservation(int borrowId, int readerId, int itemId, LocalDate dueBy, String reservationType){

        }
        private void RejectReservation(int borrowId){

        }



    }
    public Parent createContent() {
        BorderPane layout = new BorderPane();
        List<Reservation> reservationsList = new ArrayList<>()

        List<HBoxCell> list = new ArrayList<>();

        for(Reservation r: reservationsList){
            list.add(new HBoxCell(r.getReaderId(),r.getId(),r.getDueDate(),r.getType()));
        }
        ListView<RegistrationsApproveForm.HBoxCell> listView = new ListView<RegistrationsApproveForm.HBoxCell>();
        ObservableList<RegistrationsApproveForm.HBoxCell> myObservableList = FXCollections.observableList(list);

        listView.setItems(myObservableList);
        layout.setCenter(listView);

        return layout;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene((createContent()),600,500));

        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }

}
