package Utils;

import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import com.example.librarysoftware.IndexController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GUIUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {
        Parent root = null;
        try {
            root = FXMLLoader.load(GUIUtils.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void SubmitAuthor(ActionEvent e, String fxmlFile, String title, List<Integer>ids) throws IOException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(GUIUtils.class.getResource(fxmlFile));
        root = loader.load();
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();


    }
    public static void SetupItemsTableview(List<BaseLibraryItem> displayItems, TableView tableViewName){
        tableViewName.getItems().clear();
        tableViewName.getColumns().clear();
        tableViewName.refresh();

        TableColumn<BaseLibraryItem, String> column1 = new TableColumn<>(LibraryDictionary.ItemTitle);
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableViewName.getColumns().add(column1);
        TableColumn<BaseLibraryItem, String> column2 = new TableColumn<>(LibraryDictionary.ItemDesc);
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableViewName.getColumns().add(column2);
        TableColumn<BaseLibraryItem, LocalDate> column3 = new TableColumn<>(LibraryDictionary.ItemPublishDate);
        column3.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        tableViewName.getColumns().add(column3);
        TableColumn<BaseLibraryItem, String> column4 = new TableColumn<>(LibraryDictionary.ItemAuthor);
        column4.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableViewName.getColumns().add(column4);
        TableColumn<BaseLibraryItem, Integer> column5 = new TableColumn<>(LibraryDictionary.ItemQuantity);
        column5.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableViewName.getColumns().add(column5);


        tableViewName.setItems(FXCollections.observableArrayList(displayItems));

    }
    public static void SetupAlert(String message){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(message);
        a.show();
    }

}
