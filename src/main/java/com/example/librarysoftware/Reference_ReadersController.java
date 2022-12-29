package com.example.librarysoftware;

import Library.Dto.java.VisualizeItems.ReaderVisualize;
import Utils.GUIUtils;
import Utils.LibraryDictionary;
import Utils.ReferenceHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Reference_ReadersController implements Initializable {
    private List<ReaderVisualize> items = new ArrayList<>();
    @FXML
    TableView<ReaderVisualize> ReaderRef_Tableview;

    public void RefReaderBack(ActionEvent e){
        GUIUtils.changeScene(e,"/ReferenceTab.fxml", LibraryDictionary.ReferenceTitle);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = ReferenceHelper.GetAllReferenceReader();
        GUIUtils.SetupReferenceReaderTableview(items,ReaderRef_Tableview);
    }
}
