package com.example.librarysoftware;

import Library.Dto.java.DTOLibraryItems.*;
import Utils.GUIUtils;
import Utils.ItemHelper;
import Utils.LibraryDictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class ScrapItemsController implements Initializable {
    private int itemId = -1;
    private List<BaseLibraryItem> items = new ArrayList<>();
    @FXML
    private ComboBox<String> ScrapItems_FilterCombobox;
    @FXML
    private TableView<BaseLibraryItem> ScrapItems_Tableview;
    @FXML
    private Label ScrapItems_selectedLabel;

    private ObservableList<String> options= FXCollections.observableArrayList(
            LibraryDictionary.Book,
            LibraryDictionary.MusicDisc,
            LibraryDictionary.AudioBook,
            LibraryDictionary.Movies,
            LibraryDictionary.DictionariesAndOthers
    );

    public void Back(ActionEvent event) {
        GUIUtils.changeScene(event, "/LibraryOverview.fxml", LibraryDictionary.LibraryOverview);
    }
    public void Scrap(){
        if(itemId == -1){
            GUIUtils.SetupAlert("Не сте избрали артикул");
        }
        ItemHelper.ScrapItem(itemId);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = ItemHelper.GetItems();
        GUIUtils.SetupItemsTableview(items,ScrapItems_Tableview);

        ScrapItems_FilterCombobox.setItems(options);
        ScrapItems_FilterCombobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            List<BaseLibraryItem> selected= new ArrayList<>();
            switch(newValue){
                case LibraryDictionary.Book:{
                    for(BaseLibraryItem b : items){
                        if(b instanceof BookItem)
                            selected.add(b);
                    }
                }break;
                case LibraryDictionary.MusicDisc:{
                    for(BaseLibraryItem b : items){
                        if(b instanceof MusicItem)
                            selected.add(b);
                    }
                }break;
                case LibraryDictionary.AudioBook:{
                    for(BaseLibraryItem b : items){
                        if(b instanceof AudioBook)
                            selected.add(b);
                    }
                }break;
                case LibraryDictionary.Movies:{
                    for(BaseLibraryItem b : items){
                        if(b instanceof Movies)
                            selected.add(b);
                    }
                }break;

            }

        });


        ScrapItems_Tableview.setRowFactory(tv -> {
            TableRow<BaseLibraryItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    BaseLibraryItem rowData = row.getItem();
                    ScrapItems_selectedLabel.setText(rowData.getTitle());
                    itemId = rowData.getId();
                }
            });
            return row ;
        });



    }

}

