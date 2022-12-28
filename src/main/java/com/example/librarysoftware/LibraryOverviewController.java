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
    private TableView<BaseLibraryItem>LibraryOverview_Tableview;



    public void Back(ActionEvent e){
        GUIUtils.changeScene(e,"/OperatorPanel.fxml", LibraryDictionary.IndexTitle);
    }
    public void Scrap(ActionEvent e){
        GUIUtils.changeScene(e,"/ScrapItems.fxml",LibraryDictionary.ScrapTitle);
    }
    public void Archive(ActionEvent e){
        GUIUtils.changeScene(e,"/ArchiveItems.fxml",LibraryDictionary.ArchiveTitle);

    }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            items = ItemHelper.GetItems();
            GUIUtils.SetupItemsTableview(items,LibraryOverview_Tableview);


        }
}
