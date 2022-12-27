package com.example.librarysoftware;

import ExceptionHandling.LibraryException;
import ExceptionHandling.SeverityCodes;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Logger.Logger;
import Utils.GUIUtils;
import Utils.LibraryDictionary;
import Utils.ReaderHelper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReaderPanelController implements Initializable {
    private int itemId = -1;
    private List<BaseLibraryItem> reservations = new ArrayList<>();
    @FXML
    private Label ReaderPanel_labelReturn;
    @FXML
    private TableView<BaseLibraryItem> ReaderPanel_tableview;

    public void Logout(ActionEvent event){
        GUIUtils.changeScene(event,"/Index.fxml", LibraryDictionary.IndexTitle);
    }
    public void ReturnBook(){
        if(itemId == -1){
            Alert a =new Alert(Alert.AlertType.ERROR);
            a.setContentText("Не сте избрали нищо!");
            a.show();
            return;
        }
        try {
            ReaderHelper.ReturnItem(itemId);
            ReaderPanel_labelReturn.setText("");
        } catch (Exception e) {
            Logger log = new Logger();
            log.LogException(new LibraryException(e.getMessage(), SeverityCodes.Severe));
        }
    }
    public void SetupTableview(List<BaseLibraryItem> displayBorrowed){
        TableColumn<BaseLibraryItem,String>column1 = new TableColumn<>("Заглавие");
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));
        ReaderPanel_tableview.getColumns().add(column1);

     /*   TableColumn<BaseLibraryItem, Date>column2 = new TableColumn<>("Заето на:");
        column2.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        ReaderPanel_tableview.getColumns().add(column2);

        TableColumn<BaseLibraryItem,Date>column3 = new TableColumn<>("Дата на връщане:");
        column3.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        ReaderPanel_tableview.getColumns().add(column3);
*/
        ReaderPanel_tableview.setItems(FXCollections.observableArrayList(displayBorrowed));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            reservations = ReaderHelper.GetReaderItems();
        } catch (Exception e ) {
            Logger logger = new Logger();
            logger.LogException(new LibraryException(e.getMessage(), SeverityCodes.Medium));
        }

        SetupTableview(reservations);
        ReaderPanel_tableview.setRowFactory(tv -> {
            TableRow<BaseLibraryItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty()) ){
                    BaseLibraryItem rowData = row.getItem();
                    ReaderPanel_labelReturn.setText(rowData.getTitle());
                    itemId = rowData.getId();
                }
            });
            return row;
        });
    }
}
