package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.BookAuthor;
import Library.Dto.java.DTOLibraryItems.MovieDirector;
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

        BookAuthor ba = new BookAuthor(name,description);
        AuthorRepository lr = RepositoryFactory.CreateAuthorRepository();
        lr.AddObject(ba);


    }
    public void Clear(){

    }
    public void Back(){


    }
}
