package com.example.librarysoftware;

import Utils.GUIUtils;
import Utils.LibraryDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FormsSelectionController {

    public void BorrowForm(){
            ApproveBorrowForm form = new ApproveBorrowForm();
            try {
                form.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


    }
    public void ReaderForm(){
        RegistrationsApproveForm form = new RegistrationsApproveForm();
        try {
            form.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void SelectionBack(ActionEvent e){
        GUIUtils.changeScene(e,"/OperatorPanel.fxml", LibraryDictionary.OperatorPanel);

    }
}
