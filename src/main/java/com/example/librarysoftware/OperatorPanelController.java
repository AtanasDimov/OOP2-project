package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Utils.AccountHelper;
import Utils.GUIUtils;
import Utils.LibraryDictionary;
import Utils.QueryGenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OperatorPanelController implements Initializable {
    private List<ReaderAccount> accounts = new ArrayList<>();
    private int accountId = -1;
    @FXML
    private TableView<ReaderAccount> OperatorPanel_Tableview;

    private void SetupTableview(List<ReaderAccount> displayItems){

        TableColumn<ReaderAccount, String>column1 = new TableColumn<>(LibraryDictionary.ReaderUsername);
        column1.setCellValueFactory(new PropertyValueFactory<>("Username"));
        OperatorPanel_Tableview.getColumns().add(column1);
        TableColumn<ReaderAccount, String>column2 = new TableColumn<>(LibraryDictionary.ReaderFirstName);
        column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        OperatorPanel_Tableview.getColumns().add(column2);
        TableColumn<ReaderAccount, String>column3 = new TableColumn<>(LibraryDictionary.ReaderLastname);
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        OperatorPanel_Tableview.getColumns().add(column3);
        TableColumn<ReaderAccount, Integer>column4 = new TableColumn<>(LibraryDictionary.ReaderRating);
        column4.setCellValueFactory(new PropertyValueFactory<>("readerRating"));
        OperatorPanel_Tableview.getColumns().add(column4);

        OperatorPanel_Tableview.setItems(FXCollections.observableArrayList(displayItems));
    }

    public void FormsSelection(ActionEvent event){
        GUIUtils.changeScene(event,"/FormsSelector.fxml",LibraryDictionary.SelectionTitle);

    }

    public void Back(ActionEvent e){
        GUIUtils.changeScene(e,"/Index.fxml", LibraryDictionary.IndexTitle);
    };
    public void LibraryOverview(ActionEvent e){
        GUIUtils.changeScene(e,"/LibraryOverview.fxml",LibraryDictionary.LibraryOverview);
    };
    public void RemoveReader(){
        AccountHelper.UnsignReader(accountId);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<ReaderAccount> accounts = (List<ReaderAccount>) (Object)repository.GetListOfObject(QueryGenerator.GetAllReaders());
        repository.CloseSession();
        SetupTableview(accounts);

        OperatorPanel_Tableview.setRowFactory(tv -> {
            TableRow<ReaderAccount> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ReaderAccount rowData = row.getItem();
                    accountId = rowData.getAccountId();
                }
            });
            return row ;
        });


    }

}
