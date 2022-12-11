package Utils;

import com.example.librarysoftware.LoggedController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIUtils {
    public static void changeScene(ActionEvent event,String fxmlFile,String title,String username,String password){
        Parent root = null;
        if (username !=null && password !=null){
            try{
                FXMLLoader loader = new FXMLLoader(GUIUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedController lc = loader.getController();

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

}
