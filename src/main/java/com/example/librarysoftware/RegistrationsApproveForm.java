package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.Form.Form;
import Utils.FormHelper;
import Utils.QueryGenerator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class RegistrationsApproveForm extends Application {

    public static class HBoxCell extends HBox {
        Label readerFirstName = new Label();
        Label readerLastName = new Label();
        Label readerUsername = new Label();
        Button btnAdd = new Button();
        Button btnRemove = new Button();

        HBoxCell(String firstName, String lastName,String username, String password, int formId) {
            super();


            readerFirstName.setText(firstName + " ");
            readerLastName.setText(lastName + " ");
            readerUsername.setText(username + " ");
            btnAdd.setText("Одобри");
            btnRemove.setText("Отхвърли");

            btnAdd.setOnAction(event -> AddForm(formId));
            btnRemove.setOnAction(event -> RemoveForm(formId));
            this.getChildren().addAll(readerFirstName,readerLastName,readerUsername,btnAdd,btnRemove);


        }
        private void AddForm(int formId){
            FormHelper.AcceptForm(formId);
        }
        private void RemoveForm(int formId){
            FormHelper.DeclineForm(formId);
        }



    }
    public Parent createContent() {
        BorderPane layout = new BorderPane();
        List<Form> registerForms = new ArrayList<>();
        registerForms = FormHelper.GetAllActiveForms();
        List<HBoxCell> list = new ArrayList<>();

        for(Form r: registerForms){
            list.add(new HBoxCell(r.getFirstName(),r.getLastName(),r.getUsername(),r.getPassword(),r.getId()));
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
        stage.setScene(new Scene((createContent()),600,500));

        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }




}
