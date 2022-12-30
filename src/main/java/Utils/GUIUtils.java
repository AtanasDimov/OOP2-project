package Utils;

import Library.Dto.java.DTOAccount.ReaderAccount;
import Library.Dto.java.DTOLibraryItems.BaseLibraryItem;
import Library.Dto.java.Form.Form;
import Library.Dto.java.VisualizeItems.ReaderVisualize;
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
import java.util.Date;
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
    public static void SetupFormsTableView(List<Form> displayItems,TableView tableView){


        TableColumn<Form, Date>column1 = new TableColumn<>(LibraryDictionary.DateOfRegistry);
        column1.setCellValueFactory(new PropertyValueFactory<>("DateOfRegistry"));
        tableView.getColumns().add(column1);
        TableColumn<Form, Boolean>column2 = new TableColumn<>(LibraryDictionary.Status);
        column2.setCellValueFactory(new PropertyValueFactory<>("IsAccepted"));
        tableView.getColumns().add(column2);
        TableColumn<Form, String>column3 = new TableColumn<>(LibraryDictionary.ReaderUsername);
        column3.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tableView.getColumns().add(column3);
        TableColumn<Form, String>column4 = new TableColumn<>(LibraryDictionary.ReaderFirstName);
        column4.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tableView.getColumns().add(column4);

        tableView.setItems(FXCollections.observableArrayList(displayItems));

    }
    public static void SetupReferenceReaderTableview(List<ReaderVisualize>displayItems, TableView tableView){
        TableColumn<ReaderVisualize,LocalDate>column1 = new TableColumn<>(LibraryDictionary.ReaderDate);
        column1.setCellValueFactory(new PropertyValueFactory<>("firstRegistration"));
        tableView.getColumns().add(column1);
        TableColumn<ReaderVisualize,List<BaseLibraryItem>>column2 = new TableColumn<>(LibraryDictionary.ReaderItems);
        column2.setCellValueFactory(new PropertyValueFactory<>("readerItems"));
        tableView.getColumns().add(column2);
        TableColumn<ReaderVisualize,String>column3 = new TableColumn<>(LibraryDictionary.ReaderFirstName);
        column3.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableView.getColumns().add(column3);
        TableColumn<ReaderVisualize,String>column4 = new TableColumn<>(LibraryDictionary.ReaderLastname);
        column4.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableView.getColumns().add(column4);

        tableView.setItems(FXCollections.observableArrayList(displayItems));
    }

}
