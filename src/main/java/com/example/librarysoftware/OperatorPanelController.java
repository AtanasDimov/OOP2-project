package com.example.librarysoftware;

import Utils.GUIUtils;
import Utils.LibraryDictionary;
import javafx.event.ActionEvent;

public class OperatorPanelController {
    public void Back(ActionEvent e){
        GUIUtils.changeScene(e,"/Index.fxml", LibraryDictionary.IndexTitle);
    };
    public void LibraryOverview(ActionEvent e){
        GUIUtils.changeScene(e,"/LibraryOverview.fxml",LibraryDictionary.LibraryOverview);
    };

}
