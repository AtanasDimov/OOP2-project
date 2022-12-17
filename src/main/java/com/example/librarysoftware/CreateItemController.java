package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.*;
import Utils.GUIUtils;
import Utils.ItemFactory;
import Utils.ItemHelper;
import Utils.QueryGenerator;
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

    @FXML
    private ComboBox<MoviesAgeRating> CreateItem_MovieRating;
    @FXML
    private DatePicker CreateItem_PublishDate;
    @FXML
    private ComboBox<String> CreateItem_Combobox;
    @FXML
    private Button CreateItem_btnAuthor;
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
            "Книга",
            "Музикални дискове/плочи",
            "Аудио Книга",
            "Филми",
            "Речници,ръководства, методики и др."
    );


    public void Submit(ActionEvent event){
        String title = CreateItem_DynamicText1.getText();
        String description = CreateItem_DynamicText2.getText();
        String quantity = CreateItem_DynamicText3.getText();



        switch(CreateItem_Combobox.getValue()){
            case "Книга":{

                String pageCount = CreateItem_DynamicText4.getText();
                BookItem book = ItemFactory.CreateBookItem(title,description, LocalDate.now(),Integer.parseInt(quantity),Integer.parseInt(pageCount));
                ItemHelper.CreateItem(book,ids);

            }break;
            case "Музикални дискове/плочи":{
                String runtime = CreateItem_DynamicText4.getText();
                String album = CreateItem_DynamicText5.getText();
               // MusicItem music = ItemFactory.CreateMusicItem(title,description, LocalDate.now())
                MusicItem musicItem = ItemFactory.CreateMusicItem(title,description,LocalDate.now(),Integer.parseInt(quantity),Integer.parseInt(runtime),album);
                ItemHelper.CreateItem(musicItem,ids);
            }break;
            case "Аудио Книга":{
                String runtime = CreateItem_DynamicText4.getText();
                AudioBook audioBook = ItemFactory.CreateAudioBook(title,description,LocalDate.now(),Integer.parseInt(quantity),Integer.parseInt(runtime));
                ItemHelper.CreateItem(audioBook,ids);
            }break;
            case "Филми":{
                String runtime = CreateItem_DynamicText4.getText();
                String videoQuality = CreateItem_DynamicText5.getText();
                String rating = CreateItem_MovieRating.getValue().toString();
                Movies movie = ItemFactory.CreateMovie(title,description,LocalDate.now(),Integer.parseInt(quantity),Integer.parseInt(runtime),videoQuality,rating);
            }break;


        }

    }
    public void Clear(){
        CreateItem_DynamicText1.clear();
        CreateItem_DynamicText2.clear();
        CreateItem_DynamicText3.clear();
        CreateItem_DynamicText4.clear();
        CreateItem_DynamicText5.clear();
        CreateItem_Combobox.setValue("Книга");
        Author_combobox.setValue(null);

    }
    public void Back(){

    }
    public void ChooseAuthor(){
      /*  Stage stage = new Stage();
        VisualiseAuthor vs = new VisualiseAuthor();
        try{vs.start(stage);}catch (Exception ex){}*/
    }
    public void AddAuthor(ActionEvent event){
        GUIUtils.changeScene(event,"/CreateAuthor.fxml","Create");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CreateItem_DynamicText1.setPromptText("Заглавие");
        CreateItem_DynamicText2.setPromptText("Описание");
        CreateItem_DynamicText3.setPromptText("Количество");
        CreateItem_DynamicText4.setPromptText("Брой страници");
        CreateItem_DynamicText5.setVisible(false);
        CreateItem_MovieRating.setVisible(false);
        CreateItem_PublishDate.setVisible(false);
        //CreateItem_DynamicText1.setPromptText("Дата на публикация");

        CreateItem_Combobox.setItems(options);
        CreateItem_Combobox.setValue("Книга");
        CreateItem_Combobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch(newValue){
                case "Книга": {
                CreateItem_DynamicText4.setPromptText("Брой страници");
                CreateItem_DynamicText5.setVisible(false);
            }break;
                case "Музикални дискове/плочи":{
                CreateItem_DynamicText5.setVisible(true);
                CreateItem_DynamicText4.setPromptText("Продължителност");
                CreateItem_DynamicText5.setPromptText("Албум");
            }break;
                case"Аудио Книга":{
                CreateItem_DynamicText4.setPromptText("Продължителност");
                CreateItem_DynamicText5.setVisible(false);
            }break;
                case "Филми": {
                CreateItem_DynamicText5.setVisible(true);
                CreateItem_MovieRating.setVisible(true);
                CreateItem_MovieRating.getItems().setAll(MoviesAgeRating.values());
                CreateItem_DynamicText4.setPromptText("Продължителност");
                CreateItem_DynamicText5.setPromptText("Видео Качество");

            }}

        });

        List<Author> authors = new ArrayList<>();
        AuthorRepository ar = RepositoryFactory.CreateAuthorRepository();
        authors = (List<Author>)(Object) ar.GetListOfObject(QueryGenerator.GetListOfAuthors());
        Author_combobox.getItems().setAll(authors);
        Author_combobox.setOnAction(event -> {
            Author selecteditem = Author_combobox.getSelectionModel().getSelectedItem();
            ids.add(selecteditem.getId());
            System.out.println(ids.get(0));

        });


    }
}
