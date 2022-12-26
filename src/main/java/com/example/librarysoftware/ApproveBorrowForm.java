package com.example.librarysoftware;

import ExceptionHandling.MissingReservationsException;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.BorrowForm;
import Library.Dto.java.DTOLibraryItems.Reservation;
import Library.Dto.java.DTOLibraryItems.ReservationDueDates;
import Library.Dto.java.DTOLibraryItems.ReservationTypes;
import Library.Dto.java.Form.Form;
import Utils.FormHelper;
import Utils.QueryGenerator;
import Utils.ReaderHelper;
import Utils.ReservationHelper;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
import java.util.Date;
import java.util.List;

public class ApproveBorrowForm extends Application {
    public static class HBoxCell extends HBox {
        Label readerUsername = new Label();
        Label ItemTitle = new Label();
        Button btnApprove = new Button();
        Button btnReject = new Button();
        ComboBox<String> dueDates_Combobox = new ComboBox<>();
        ComboBox<String> reservationTypes_Combobox = new ComboBox<>();

        HBoxCell(int borrowFormId,int readerId, int itemId) {
            super();
            String dueDate = "";
            String reservationType = "";
            ObservableList<String> dueDatesList = FXCollections.observableArrayList();
            ObservableList<String> reservationsTypeList = FXCollections.observableArrayList();

            Arrays.stream(ReservationDueDates.values()).forEach(d -> dueDatesList.add(d.toString()));
            Arrays.stream(ReservationTypes.values()).forEach(d -> reservationsTypeList.add(d.toString()));

            dueDates_Combobox.setItems(dueDatesList);
            dueDates_Combobox.setPromptText("Период на заемане");
            reservationTypes_Combobox.setItems(reservationsTypeList);
            reservationTypes_Combobox.setPromptText("Вид на заемане");

            dueDates_Combobox.setValue("Изберете период за отдаване");
            dueDates_Combobox.setOnAction(event -> { ChooseDueDate(dueDate);});


            reservationTypes_Combobox.setValue("Изберете вид отдаване:");
            reservationTypes_Combobox.setOnAction(event -> { });



            readerUsername.setText(readerId + " ");
            ItemTitle.setText(itemId+ " ");

            btnApprove.setText("Одобри");
            btnReject.setText("Отхвърли");

            btnApprove.setOnAction(event -> ApproveReservation(borrowFormId,dueDate,reservationType));
            btnReject.setOnAction(event -> RejectReservation(borrowFormId));
            this.getChildren().addAll(readerUsername,ItemTitle,dueDates_Combobox,reservationTypes_Combobox,btnApprove,btnReject);


        }

        private void ChooseDueDate(String date) {
            date =dueDates_Combobox.getValue();
        }
        private void ChooseReservationType(String type){
            type =reservationTypes_Combobox.getValue();
        }

        private void ApproveReservation(int borrowFormId,  String reservationDueDate, String reservationType){
            ReservationHelper.AddReservation(borrowFormId, reservationDueDate, reservationType);
        }
        private void RejectReservation(int borrowFormId){
            ReservationHelper.CancelReservation(borrowFormId);
        }



    }
    public Parent createContent() {
        BorderPane layout = new BorderPane();
        List<BorrowForm> borrowFormList = new ArrayList<>();
        LibraryRepository lr = RepositoryFactory.CreateLibraryRepository();
        borrowFormList = (List<BorrowForm>)(Object)lr.GetListOfObject(QueryGenerator.GetBorrowForms());
        lr.CloseSession();
        List<HBoxCell> list = new ArrayList<>();
        for(BorrowForm b: borrowFormList){
            list.add(new HBoxCell(b.getId(), b.getReaderId(),b.getItemId()));
        }
        ListView<HBoxCell> listView = new ListView<HBoxCell>();
        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);

        listView.setItems(myObservableList);
        layout.setCenter(listView);

        return layout;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene((createContent()),900,500));

        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }

}
