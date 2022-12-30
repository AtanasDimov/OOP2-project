package com.example.librarysoftware;

import Library.Dto.java.Form.Form;
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

public class Reference_FormsController implements Initializable {
    private List<Form> items = new ArrayList<>();

    @FXML
    private TableView<Form> Forms_Tableview;

    public void FormsBack(ActionEvent e){
        GUIUtils.changeScene(e,"/ReferenceTab.fxml", LibraryDictionary.ReferenceTitle);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = ReferenceHelper.GetAllReferenceForms();
        GUIUtils.SetupFormsTableView(items,Forms_Tableview);
    }
}
