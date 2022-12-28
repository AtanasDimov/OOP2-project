package com.example.librarysoftware;

import Library.Dto.java.DTOLibraryItems.Author;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Utils.GUIUtils;
import Utils.ItemHelper;
import Utils.LibraryDictionary;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryOverviewController implements Initializable {
    private List<BaseLibraryItem> items = new ArrayList<>();

    @FXML
    private TableView<BaseLibraryItem>LibraryOverview_Itemstableview;



    public void Back(ActionEvent e){
        GUIUtils.changeScene(e,"/OperatorPanel.fxml", LibraryDictionary.IndexTitle);
    }
    public void Scrap(){
        //GUIUtils.changeScene();
    }
    public void Archive(){

    }

    public void SetupTableview(List<BaseLibraryItem> displayItems){
        TableColumn<BaseLibraryItem, String> column1 = new TableColumn<>(LibraryDictionary.ItemTitle);
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));
        LibraryOverview_Itemstableview.getColumns().add(column1);
        TableColumn<BaseLibraryItem, String> column2 = new TableColumn<>(LibraryDictionary.ItemDesc);
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));
        LibraryOverview_Itemstableview.getColumns().add(column2);
        TableColumn<BaseLibraryItem, LocalDate> column3 = new TableColumn<>(LibraryDictionary.ItemPublishDate);
        column3.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        LibraryOverview_Itemstableview.getColumns().add(column3);
        TableColumn<BaseLibraryItem, String> column4 = new TableColumn<>(LibraryDictionary.ItemAuthor);
        column4.setCellValueFactory(new PropertyValueFactory<>("author"));
        LibraryOverview_Itemstableview.getColumns().add(column4);
        TableColumn<BaseLibraryItem, Integer> column5 = new TableColumn<>(LibraryDictionary.ItemQuantity);
        column5.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        LibraryOverview_Itemstableview.getColumns().add(column5);



        LibraryOverview_Itemstableview.setItems(FXCollections.observableArrayList(displayItems));

    }


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            items = ItemHelper.GetItems();

            SetupTableview(items);


        }
}
