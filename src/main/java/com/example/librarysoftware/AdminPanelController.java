package com.example.librarysoftware;

import Hibernate.Control.Main.Repository.LibraryRepository;
import Hibernate.Control.Main.Repository.RepositoryFactory;
import Library.Dto.java.DTOAccount.OperatorAccount;
import Utils.AccountHelper;
import Utils.GUIUtils;
import Utils.LibraryDictionary;
import Utils.QueryGenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AdminPanelController implements Initializable {
    private int operatorId = -1;
    private List<OperatorAccount> operatorAccounts = new ArrayList<>();
    @FXML
    private Button AdminPanel_btnRemove;
    @FXML
    private Button AdminPanel_btnAdd;
    @FXML
    private Button AdminPanel_btnBack;
    @FXML
    private Button AdminPanel_btnOpenAlerts;
    @FXML
    private TableView<OperatorAccount> AdminPanel_Tableview;

    public void AddOperator(ActionEvent event){
        GUIUtils.changeScene(event,"/RegisterOperator.fxml",LibraryDictionary.CreateOperatorTitle);
    }
    public void RemoveOperator(){
        AccountHelper.DeleteOperator(operatorId);
    }
    private void SetupTableview(List<OperatorAccount> displayItems){
        TableColumn<OperatorAccount, Integer>column1 = new TableColumn<>(LibraryDictionary.IdAttribute);
        column1.setCellValueFactory(new PropertyValueFactory<>("accountId"));
        AdminPanel_Tableview.getColumns().add(column1);

        TableColumn<OperatorAccount, String> column2 = new TableColumn<>(LibraryDictionary.OperatorUsername);
        column2.setCellValueFactory(new PropertyValueFactory<>("username"));
        AdminPanel_Tableview.getColumns().add(column2);
        AdminPanel_Tableview.setItems(FXCollections.observableArrayList(displayItems));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdminPanel_btnOpenAlerts.setOnAction(event -> {
            GUIUtils.changeScene(event,"/src/main/resources/AdminPanel.fxml","Контролен панел/известия");
        });
        AdminPanel_btnBack.setOnAction(event -> {GUIUtils.changeScene(event,"/Index.fxml","Индекс");});

        LibraryRepository repository = RepositoryFactory.CreateLibraryRepository();
        List<OperatorAccount> operatorAccounts = (List<OperatorAccount>) (Object)repository.GetListOfObject(QueryGenerator.GetAllOperators());
        repository.CloseSession();
        SetupTableview(operatorAccounts);

        AdminPanel_Tableview.setRowFactory(tv -> {
            TableRow<OperatorAccount> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    OperatorAccount rowData = row.getItem();
                    operatorId = rowData.getAccountId();
                }
            });
            return row ;
        });
    }

}
