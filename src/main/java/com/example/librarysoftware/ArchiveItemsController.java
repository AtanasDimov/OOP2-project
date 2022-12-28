package com.example.librarysoftware;

import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Utils.GUIUtils;
import Utils.ItemHelper;
import Utils.LibraryDictionary;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;


import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class ArchiveItemsController implements Initializable {

    private int itemId = -1;
    private List<BaseLibraryItem> items;
    @FXML
    private Label ArchiveItems_selectedLabel;
    @FXML
    private TableView<BaseLibraryItem> ArchiveItems_Tableview;
    public void Back(ActionEvent event){
        GUIUtils.changeScene(event,"/LibraryOverview.fxml", LibraryDictionary.LibraryOverview);
    }
    public void Archive(){
        if (itemId == -1){
            GUIUtils.SetupAlert("Няма направен избор");
        }
        ItemHelper.ArchiveItem(itemId);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = ItemHelper.GetItemsForArchive();
        GUIUtils.SetupItemsTableview(items,ArchiveItems_Tableview);

        ArchiveItems_Tableview.setRowFactory(tv -> {
            TableRow<BaseLibraryItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    BaseLibraryItem rowData = row.getItem();
                    ArchiveItems_selectedLabel.setText(rowData.getTitle());
                    itemId = rowData.getId();
                }
            });
            return row ;
        });

    }
}
