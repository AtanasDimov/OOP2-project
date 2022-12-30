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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Reference_ItemController implements Initializable {
    private List<BaseLibraryItem> activeItems = new ArrayList<>();
    private List<BaseLibraryItem> scrappedItems = new ArrayList<>();
    @FXML
    private ComboBox<String> ReferenceItem_Combobox;
    @FXML
    private TableView<BaseLibraryItem> ReferenceItem_Tableview;
    private ObservableList<String> options= FXCollections.observableArrayList(
            LibraryDictionary.DisplayActive,
            LibraryDictionary.DisplayScrapped

    );



    public void RefItemBack(ActionEvent e){
        GUIUtils.changeScene(e,"/ReferenceTab.fxml", LibraryDictionary.ReferenceTitle);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activeItems = ItemHelper.GetItems();
        //scrappedItems = ItemHelper.GetScrappedItems();


        ReferenceItem_Combobox.setItems(options);
        ReferenceItem_Combobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case LibraryDictionary.DisplayActive: {
                    GUIUtils.SetupItemsTableview(activeItems, ReferenceItem_Tableview);
                }
                break;
                case LibraryDictionary.DisplayScrapped: {
                    //GUIUtils.SetupItemsTableview(scrappedItems,ReferenceItem_Tableview);
                    System.out.println("Bachka");
                }
                break;
            }

        });
        }
    }



