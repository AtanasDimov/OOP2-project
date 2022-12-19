package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.*;
import Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CreateItemController implements Initializable {
    private  List<Integer> ids= new ArrayList<>();

    List<Author> authors = new ArrayList<>();

    @FXML
    private ComboBox<MoviesAgeRating> CreateItem_MovieRating;
    @FXML
    private DatePicker CreateItem_PublishDate;
    @FXML
    private ComboBox<String> CreateItem_Combobox;
    @FXML
    private TextField CreateItem_DynamicText1;
    @FXML
    private TextField CreateItem_DynamicText2;
    @FXML
    private TextField CreateItem_DynamicText3;
    @FXML
    private TextField CreateItem_DynamicText4;
    @FXML
    private TextField CreateItem_DynamicText5;
    @FXML
    private ComboBox<Author> Author_combobox;

    private ObservableList<String>options= FXCollections.observableArrayList(
            LibraryDictionary.Book,
            LibraryDictionary.MusicDisc,
            LibraryDictionary.AudioBook,
            LibraryDictionary.Movies,
            LibraryDictionary.DictionariesAndOthers
    );


    public void Submit(ActionEvent event){
        String title = CreateItem_DynamicText1.getText();
        String description = CreateItem_DynamicText2.getText();
        String quantity = CreateItem_DynamicText3.getText();
        LocalDate publishDate = CreateItem_PublishDate.getValue();



        switch(CreateItem_Combobox.getValue()){
            case LibraryDictionary.Book:{

                String pageCount = CreateItem_DynamicText4.getText();
                BookItem book = ItemFactory.CreateBookItem(title,description, publishDate,Integer.parseInt(quantity),Integer.parseInt(pageCount));
                ItemHelper.CreateItem(book,ids);

            }break;
            case LibraryDictionary.MusicDisc:{
                String runtime = CreateItem_DynamicText4.getText();
                String album = CreateItem_DynamicText5.getText();
                MusicItem musicItem = ItemFactory.CreateMusicItem(title,description,publishDate,Integer.parseInt(quantity),Integer.parseInt(runtime),album);
                ItemHelper.CreateItem(musicItem,ids);
            }break;
            case LibraryDictionary.AudioBook:{
                String runtime = CreateItem_DynamicText4.getText();
                AudioBook audioBook = ItemFactory.CreateAudioBook(title,description,publishDate,Integer.parseInt(quantity),Integer.parseInt(runtime));
                ItemHelper.CreateItem(audioBook,ids);
            }break;
            case LibraryDictionary.Movies:{
                String runtime = CreateItem_DynamicText4.getText();
                String videoQuality = CreateItem_DynamicText5.getText();
                String rating = CreateItem_MovieRating.getValue().toString();
                Movies movie = ItemFactory.CreateMovie(title,description,publishDate,Integer.parseInt(quantity),Integer.parseInt(runtime),videoQuality,rating);
            }break;


        }

    }
    public void Clear(){
        CreateItem_DynamicText1.clear();
        CreateItem_DynamicText2.clear();
        CreateItem_DynamicText3.clear();
        CreateItem_DynamicText4.clear();
        CreateItem_DynamicText5.clear();
        CreateItem_Combobox.setValue(LibraryDictionary.Book);
        changeAuthors(LibraryDictionary.Book);
        ids = new ArrayList<>();

    }
    public void Back(ActionEvent event){
        GUIUtils.changeScene(event,"/Index.fxml",LibraryDictionary.IndexTitle);
    }
    public void AddAuthor(ActionEvent event){
        GUIUtils.changeScene(event,"/CreateAuthor.fxml",LibraryDictionary.CreateTitle);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CreateItem_DynamicText1.setPromptText(LibraryDictionary.Title);
        CreateItem_DynamicText2.setPromptText(LibraryDictionary.Description);
        CreateItem_DynamicText3.setPromptText(LibraryDictionary.Quantity);
        CreateItem_DynamicText4.setPromptText(LibraryDictionary.NumberOfPages);
        CreateItem_DynamicText5.setVisible(false);
        CreateItem_MovieRating.setVisible(false);
        CreateItem_PublishDate.setPromptText(LibraryDictionary.PublishDate);

        CreateItem_Combobox.setItems(options);
        CreateItem_Combobox.setValue(LibraryDictionary.Book);
        changeAuthors(LibraryDictionary.Book);
        CreateItem_Combobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch(newValue){
                case LibraryDictionary.Book: {
                    changeAuthors(newValue);
                    CreateItem_DynamicText4.setPromptText(LibraryDictionary.NumberOfPages);
                    CreateItem_DynamicText5.setVisible(false);
                }break;
                case LibraryDictionary.MusicDisc:{
                    changeAuthors(newValue);
                    CreateItem_DynamicText5.setVisible(true);
                    CreateItem_DynamicText4.setPromptText(LibraryDictionary.Runtime);
                    CreateItem_DynamicText5.setPromptText(LibraryDictionary.Album);
                }break;
                case LibraryDictionary.AudioBook:{
                    changeAuthors(newValue);
                    CreateItem_DynamicText4.setPromptText(LibraryDictionary.Runtime);
                    CreateItem_DynamicText5.setVisible(false);
                }break;
                case LibraryDictionary.Movies: {
                    changeAuthors(newValue);
                    CreateItem_DynamicText5.setVisible(true);
                    CreateItem_MovieRating.setVisible(true);
                    CreateItem_MovieRating.getItems().setAll(MoviesAgeRating.values());
                    CreateItem_DynamicText4.setPromptText(LibraryDictionary.Runtime);
                    CreateItem_DynamicText5.setPromptText(LibraryDictionary.VideoQuality);
                }}

        });



    }

    private void changeAuthors(String item){
        ids = new ArrayList<>();
        AuthorRepository repository = RepositoryFactory.CreateAuthorRepository();

        if(authors.size() == 0)
        {
            authors = (List<Author>) (Object)repository.GetListOfObject(QueryGenerator.GetListOfAuthors());
        }
        List<Author> selected = new ArrayList<>();
        switch(item){
            case LibraryDictionary.Book:{
                for(Author a : authors){
                    if(a instanceof BookAuthor)
                        selected.add(a);
                }
            }
            break;
            case LibraryDictionary.MusicDisc: {
                for(Author a : authors){
                    if(a instanceof MusicArtist)
                        selected.add(a);
                }
            }
            break;
            case LibraryDictionary.AudioBook: {
                for(Author a : authors){
                    if(a instanceof AudioBookNarrator)
                        selected.add(a);
                }
            }
            break;
            case LibraryDictionary.Movies: {
                for(Author a : authors){
                    if(a instanceof MovieDirector)
                        selected.add(a);
                }
            }
        }

        repository.CloseSession();
        Author_combobox.getItems().setAll(selected);
        Author_combobox.setOnAction(event -> {
            Author selecteditem = Author_combobox.getSelectionModel().getSelectedItem();
            ids.add(selecteditem.getId());
            System.out.println(ids.get(0));

        });
    }
}
