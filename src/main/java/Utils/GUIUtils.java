package Utils;

import com.example.librarysoftware.IndexController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GUIUtils {
    public static void changeScene(ActionEvent event,String fxmlFile,String title,String username,String password){
        Parent root = null;
        if (username !=null && password !=null){
            try{
                FXMLLoader loader = new FXMLLoader(GUIUtils.class.getResource(fxmlFile));
                root = loader.load();
                IndexController ic = loader.getController();

            }catch(IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(GUIUtils.class.getResource(fxmlFile));
            }catch (IOException e) {
            e.printStackTrace();
            }
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

}
