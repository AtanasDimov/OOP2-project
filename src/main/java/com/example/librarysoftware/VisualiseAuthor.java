package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.AuthorRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOLibraryItems.Author;
import Utils.GUIUtils;
import Utils.QueryGenerator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class VisualiseAuthor extends Application {
    private static List<Integer> ids= new ArrayList<>();
    public static class HBoxCell extends HBox {
        Label AuthorName = new Label();
        Label AuthorDesc = new Label();
        Button ButtonAdd = new Button();

        HBoxCell(String nameText, String descText,int id) {
            super();


            AuthorName.setText(nameText);
            AuthorDesc.setText(descText);
            ButtonAdd.setText("Add");
            ButtonAdd.setOnAction(event -> AddToList(id));
            this.getChildren().addAll(AuthorName, AuthorDesc, ButtonAdd);

        }
        public void AddToList(int id){
            ids.add(id);
            System.out.println(ids.get(0));
        }
    }
    public Parent createContent() {
        BorderPane layout = new BorderPane();

        List<Author> authors = new ArrayList<>();
        AuthorRepository ar = RepositoryFactory.CreateAuthorRepository();
        authors = (List<Author>)(Object) ar.GetListOfObject(QueryGenerator.GetListOfAuthors());
        List<HBoxCell> list = new ArrayList<>();

        for (Author a:authors) {
            list.add(new HBoxCell( a.getName()+" ",a.getDescription()+" ",a.getId()));
        }
        ListView<HBoxCell> listView = new ListView<HBoxCell>();
        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        layout.setCenter(listView);
        Button btnSubmit = new Button();

        return layout;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setWidth(300);
        stage.setHeight(500);
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }




}
