package com.example.librarysoftware;

import ExceptionHandling.LibraryException;
import ExceptionHandling.NotLoggedException;
import ExceptionHandling.SeverityCodes;
import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.ItemRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.*;
import Logger.Logger;
import Utils.GUIUtils;
import Utils.ItemHelper;
import Utils.LibraryDictionary;
import Utils.ReaderHelper;
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

public class BorrowItemController implements Initializable {

    private List<BaseLibraryItem> items = new ArrayList<>();
    private int itemId = -1;
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

    public void Borrow(){
        if(itemId == -1){
            GUIUtils.SetupAlert("Трябва да изберете артикул!");
            return;
        }
        try {
            ReaderHelper.BorrowItem(itemId);
            BorrowItem_SelectedItemLabel.setText(LibraryDictionary.BorrowConfirmation);
        } catch (NotLoggedException e){
            Logger log = new Logger();
            log.LogException(new LibraryException(e.getMessage(), SeverityCodes.Severe));
        }
    }

    public void Clear(){
        itemId = -1;
        BorrowItem_SelectedItemLabel.setText("");

    }

    public void Back(ActionEvent event){
        GUIUtils.changeScene(event, "/Index.fxml", LibraryDictionary.IndexTitle);

    }

    public void SetupTableview(List<BaseLibraryItem> displayItems){
        TableColumn<BaseLibraryItem, String> column1 = new TableColumn<>(LibraryDictionary.ItemTitle);
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));
        BorrowItem_Tableview.getColumns().add(column1);
        TableColumn<BaseLibraryItem, String> column2 = new TableColumn<>(LibraryDictionary.ItemDesc);
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));
        BorrowItem_Tableview.getColumns().add(column2);
        TableColumn<BaseLibraryItem, LocalDate> column3 = new TableColumn<>(LibraryDictionary.ItemPublishDate);
        column3.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        BorrowItem_Tableview.getColumns().add(column3);
        TableColumn<BaseLibraryItem, String> column4 = new TableColumn<>(LibraryDictionary.ItemAuthor);
        column4.setCellValueFactory(new PropertyValueFactory<>("author"));
        BorrowItem_Tableview.getColumns().add(column4);

        BorrowItem_Tableview.setItems(FXCollections.observableArrayList(displayItems));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        items = ItemHelper.GetItems();



        //BookItem item = repository.GetEagerBook(items.get(0).getId());
        //TableColumn<>
        //columns = Name,Description,PublishDate,Author,

        SetupTableview(items.stream().filter(i->i.getQuantity()>0).collect(Collectors.toList()));
        BorrowItem_FilterCombobox.setItems(options);
        BorrowItem_FilterCombobox.valueProperty().addListener((observable, oldValue, newValue) -> {
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

           SetupTableview(selected.stream().filter(i->i.getQuantity()>0).collect(Collectors.toList()));

        });
        BorrowItem_Tableview.setRowFactory(tv -> {
            TableRow<BaseLibraryItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    BaseLibraryItem rowData = row.getItem();
                    BorrowItem_SelectedItemLabel.setText(rowData.getTitle());
                    itemId = rowData.getId();
                }
            });
            return row ;
        });


    }


}
