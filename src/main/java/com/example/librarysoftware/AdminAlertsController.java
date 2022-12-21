package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Utils.GUIUtils;
import Utils.QueryGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminAlertsController implements Initializable {
    @FXML
    private Button AdminAlerts_btnBack;
    @FXML
    private TableView<Alert> AdminAlerts_Tableview;

    public void Back(ActionEvent event){
        GUIUtils.changeScene(event,"/AdminPanel.fxml","Контролен панел/оператори");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Alert> alerts = new ArrayList<>();
        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        alerts = (List<Alert>) (Object) repository.GetListOfObject(QueryGenerator.GetAlerts());

        TableColumn<Alert, String> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        AdminAlerts_Tableview.getColumns().add(column1);

        TableColumn<Alert, String> column2 = new TableColumn<>("Описание");
        column2.setCellValueFactory(new PropertyValueFactory<>("message"));
        AdminAlerts_Tableview.getColumns().add(column2);

        TableColumn<Alert, LocalDate> column3 = new TableColumn<>("Дата");
        column3.setCellValueFactory(new PropertyValueFactory<>("dateOfAlert"));
        AdminAlerts_Tableview.getColumns().add(column3);

        TableColumn<Alert, String> column4 = new TableColumn<>("Важност");
        column4.setCellValueFactory(new PropertyValueFactory<>("Severity"));
        AdminAlerts_Tableview.getColumns().add(column4);

        AdminAlerts_Tableview.setItems(FXCollections.observableArrayList(alerts));

    }
}
