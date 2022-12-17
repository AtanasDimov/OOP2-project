package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.BookAuthor;
import Library.Dto.java.DTOLibraryItems.MovieDirector;
import Utils.AuthorFactory;
import Utils.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateAuthorController {
    @FXML
    private TextField CreateAuthor_txtName;
    @FXML
    private TextField CreateAuthor_txtDescription;

    public void Submit(){
        String name = CreateAuthor_txtName.getText();
        String description = CreateAuthor_txtDescription.getText();

        BookAuthor bookAuthor = AuthorFactory.CreateBookAuthor(name, description);
        AuthorRepository repository = RepositoryFactory.CreateAuthorRepository();
        repository.AddObject(bookAuthor);


    }
    public void Clear(){
        CreateAuthor_txtName.clear();
        CreateAuthor_txtDescription.clear();

    }
    public void Back(ActionEvent event){
        GUIUtils.changeScene(event,"/CreateItem.fxml","Добавяне на книга");


    }
}
