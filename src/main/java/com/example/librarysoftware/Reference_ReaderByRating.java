package com.example.librarysoftware;

import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.*;
import Library.Dto.java.Form.Form;
import Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Reference_ReaderByRating implements Initializable {
    private List<ReaderAccount> readerAccounts = new ArrayList<>();
    @FXML
    private TableView<ReaderAccount> ReferenceReadersRating_Tableview;
    @FXML
    private ComboBox<Integer> ReferenceReadersRating_Combobox;
    private ObservableList<Integer> options= FXCollections.observableArrayList(
            1,2,3
    );

    private void SetupTableview(List<ReaderAccount> displayItems){
        ReferenceReadersRating_Tableview.getItems().clear();
        ReferenceReadersRating_Tableview.getColumns().clear();
        ReferenceReadersRating_Tableview.refresh();

        TableColumn<ReaderAccount, Date> column1 = new TableColumn<>(LibraryDictionary.DateOfRegistry);
        column1.setCellValueFactory(new PropertyValueFactory<>("firstRegistration"));
        ReferenceReadersRating_Tableview.getColumns().add(column1);
        TableColumn<ReaderAccount, String>column2 = new TableColumn<>(LibraryDictionary.ReaderUsername);
        column2.setCellValueFactory(new PropertyValueFactory<>("Username"));
        ReferenceReadersRating_Tableview.getColumns().add(column2);
        TableColumn<ReaderAccount, String>column3 = new TableColumn<>(LibraryDictionary.ReaderFirstName);
        column3.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ReferenceReadersRating_Tableview.getColumns().add(column3);
        TableColumn<ReaderAccount, String>column4 = new TableColumn<>(LibraryDictionary.ReaderLastname);
        column4.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ReferenceReadersRating_Tableview.getColumns().add(column4);

        ReferenceReadersRating_Tableview.setItems(FXCollections.observableArrayList(displayItems));
    }

    public void RefRatingBack(ActionEvent event){
        GUIUtils.changeScene(event,"/ReferenceTab.fxml", LibraryDictionary.ReferenceTitle);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ReferenceReadersRating_Combobox.setItems(options);
        ReferenceReadersRating_Combobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            readerAccounts = ReferenceHelper.GetAllReferenceReadersByRating(newValue);
            SetupTableview(readerAccounts);
        });
    }
}
