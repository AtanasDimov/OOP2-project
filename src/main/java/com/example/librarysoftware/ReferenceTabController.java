package com.example.librarysoftware;

import Utils.GUIUtils;
import Utils.LibraryDictionary;
import javafx.event.ActionEvent;

public class ReferenceTabController {

    public void ReferenceBack(ActionEvent e){
        GUIUtils.changeScene(e,"/Index.fxml", LibraryDictionary.IndexTitle);
    }
    public void AllForms(ActionEvent e){
        GUIUtils.changeScene(e,"/Reference_Forms.fxml", LibraryDictionary.ReferenceFormsTitle);
    }
    public void AllItems(ActionEvent e){
        GUIUtils.changeScene(e,"/Reference_Items.fxml", LibraryDictionary.ReferenceItemsTitle);
    }
    public void AllReaders(ActionEvent e){
        GUIUtils.changeScene(e,"/Reference_Readers.fxml",LibraryDictionary.ReferenceReadersTitle);
    }
    public void ReadersByRating(ActionEvent e){
        GUIUtils.changeScene(e,"/Reference_ReadersRating.fxml",LibraryDictionary.ReferenceRatingTitle);
    }


}
