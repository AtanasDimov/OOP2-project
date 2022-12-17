package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.*;
import Utils.AuthorFactory;
import Utils.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.lang.invoke.SwitchPoint;

public class CreateAuthorController {
    @FXML
    private TextField CreateAuthor_txtName;
    @FXML
    private TextField CreateAuthor_txtDescription;
    @FXML
    private ComboBox<String> CreateAuthor_ComboBox;

    private ObservableList<String> options= FXCollections.observableArrayList(
            "Лит. Автор",
            "Муз. Изпълнител/ Композитор",
            "Озвуч. на Аудио Книга",
            "Филмов Режисьор",
            "Издателство"
    );
    @FXML
    public void initialize() {
        CreateAuthor_ComboBox.setItems(options);
        CreateAuthor_ComboBox.setValue("Лит. Автор");
    }

    public void Submit(){
        String name = CreateAuthor_txtName.getText();
        String description = CreateAuthor_txtDescription.getText();
        switch(CreateAuthor_ComboBox.getValue()){
            case"Лит. Автор":{
                BookAuthor bookAuthor = AuthorFactory.CreateBookAuthor(name,description);
                AuthorRepository repository = RepositoryFactory.CreateAuthorRepository();
                repository.AddObject(bookAuthor);
            }break;
            case"Муз. Изпълнител/ Композитор":{
                MusicArtist musicArtist = AuthorFactory.CreateMusicArtist(name,description);
                AuthorRepository repository = RepositoryFactory.CreateAuthorRepository();
                repository.AddObject(musicArtist);
            }break;
            case"Озвуч. на Аудио Книга":{
                AudioBookNarrator narrator = AuthorFactory.CreateAudioBookNarrator(name,description);
                AuthorRepository repository = RepositoryFactory.CreateAuthorRepository();
                repository.AddObject(narrator);
            }break;
            case"Филмов Режисьор":{
                MovieDirector movieDirector = AuthorFactory.CreateMovieDirector(name,description);
                AuthorRepository repository = RepositoryFactory.CreateAuthorRepository();
                repository.AddObject(movieDirector);
            }break;
            case"Издателство":{}break;
        }


    }
    public void Clear(){
        CreateAuthor_txtName.clear();
        CreateAuthor_txtDescription.clear();
        CreateAuthor_ComboBox.setValue("Лит. Автор");

    }
    public void Back(ActionEvent event){
        GUIUtils.changeScene(event,"/CreateItem.fxml","Добавяне на книга");


    }


}
