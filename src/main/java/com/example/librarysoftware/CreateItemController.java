package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.Author;
import Utils.GUIUtils;
import Utils.QueryGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CreateItemController implements Initializable {
    private  List<Integer> ids= new ArrayList<>();


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

    public void Submit(ActionEvent event){


    }
    public void Clear(){}
    public void Back(){}
    public void ChooseAuthor(){
      /*  Stage stage = new Stage();
        VisualiseAuthor vs = new VisualiseAuthor();
        try{vs.start(stage);}catch (Exception ex){}*/
    }
    public void AddAuthor(ActionEvent event){
        GUIUtils.changeScene(event,"/CreateAuthor.fxml","Create",null,null );
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
