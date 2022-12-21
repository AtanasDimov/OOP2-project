package com.example.librarysoftware;

import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Utils.ItemHelper;
import Utils.LibraryDictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowItemController implements Initializable {

    List<BaseLibraryItem> items = new ArrayList<>();
    @FXML
    private Button BorrowItem_btnBack;
    @FXML
    private Button BorrowItem_btnClear;
    @FXML
    private Button BorrowItem_btnBorrow;
    @FXML
    private ComboBox<String> BorrowItem_FilterCombobox;
    @FXML
    private Label BorrowItem_SelectedItemLabel;
    @FXML
    private TableView<BaseLibraryItem> BorrowItem_Tableview;

    private ObservableList<String> options= FXCollections.observableArrayList(
            LibraryDictionary.Book,
            LibraryDictionary.MusicDisc,
            LibraryDictionary.AudioBook,
            LibraryDictionary.Movies,
            LibraryDictionary.DictionariesAndOthers
    );

    public void Borrow(ActionEvent event){

    }

    public void Clear(){

    }

    public void Back(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        items = ItemHelper.GetItems();
        //TableColumn<>
        //columns = Name,Description,PublishDate,Author,

        BorrowItem_FilterCombobox.setItems(options);
        /*BorrowItem_FilterCombobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch(newValue){
                case LibraryDictionary.Book:{

                }break;
                case LibraryDictionary.MusicDisc:{

                }break;
                case LibraryDictionary.AudioBook:{

                }break;
                case LibraryDictionary.Movies:{

                }break;
           }

        });*/

    }
}
