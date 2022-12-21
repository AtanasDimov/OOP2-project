package com.example.librarysoftware;

import Library.Dto.java.DTOLibraryItems.ReservationDueDates;
import Library.Dto.java.DTOLibraryItems.ReservationTypes;
import Library.Dto.java.Form.Form;
import Utils.FormHelper;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApproveBorrowForm extends Application {
    public static class HBoxCell extends HBox {
        Label readerUsername = new Label();
        Label ItemTitle = new Label();
        Button btnApprove = new Button();
        Button btnDeny = new Button();
        ComboBox<String> dueDate = new ComboBox<>();
        ComboBox<String> reservationType = new ComboBox<>();
        HBoxCell(String firstName, String lastName,String username, int formId) {
            super();
            ObservableList<String> dueDateList = FXCollections.observableArrayList();
            ObservableList<String> reservationTypeList = FXCollections.observableArrayList();
            Arrays.stream(ReservationDueDates.values()).forEach(d -> dueDateList.add(d.toString()));
            Arrays.stream(ReservationTypes.values()).forEach(d -> reservationTypeList.add(d.toString()));
            readerFirstName.setText(firstName + " ");
            readerLastName.setText(lastName + " ");
            readerUsername.setText(username + " ");
            btnApprove.setText("Одобри");
            btnDeny.setText("Отхвърли");

            btnApprove.setOnAction(event -> AddForm(formId));
            btnRemove.setOnAction(event -> RemoveForm(formId));
            this.getChildren().addAll(readerFirstName,readerLastName,readerUsername,btnAdd,btnRemove);


        }
        private void ApproveReservation(int formId,int borrowId){
            FormHelper.AcceptForm(formId);
        }
        private void DenyReservation(int formId,int borrowId){
            FormHelper.DeclineForm(formId);
        }



    }
    public Parent createContent() {
        BorderPane layout = new BorderPane();
        List<Form> registerForms = new ArrayList<>();
        registerForms = FormHelper.GetAllActiveForms();
        List<RegistrationsApproveForm.HBoxCell> list = new ArrayList<>();

        for(Form r: registerForms){
            list.add(new RegistrationsApproveForm.HBoxCell(r.getFirstName(),r.getLastName(),r.getUsername(),r.getId()));
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
