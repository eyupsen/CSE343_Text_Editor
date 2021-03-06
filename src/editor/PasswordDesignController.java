/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class Sifreyi almak icin olusan ekranin kontrolleri yapilir
 *
 * @author ilayd
 */
public class PasswordDesignController implements Initializable {

    public VBox passScene;
    public Button cancelButton;
    public Button enterButton;
    public PasswordField passText;
    public ImageView Image;
    public Pane pane;

    /**
     * Shortcuts added
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image.setImage(new Image("file:src/Assets/64x64_panda_icon.png"));

    }

    public void handleButtons() {

        //enter'a basildigi zaman sifre sisteme girilecek
        //esc'ye basildigi zaman sifre ekrani kapatilip dosya acilmayacak
        passText.setOnKeyPressed((KeyEvent key) -> {
            try {
                if (key.getCode() == KeyCode.ENTER) {

                    enter();
                }
                if (key.getCode() == KeyCode.ESCAPE) {
                    cancel();

                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });

    }

    /**
     * When the enter key is pressed,this function activated
     *
     *
     * @throws IOException
     */
    public void enter() throws IOException {

        if (TextEditor.getPasswordOfTheUser().equals(passText.getText())) {
            EditorController.receivedPassword = passText.getText();
            ((Stage) (passScene.getScene().getWindow())).close();
        } else {
            System.err.println(TextEditor.getPasswordOfTheUser() + "  " + passText.getText());
            passText.clear();
            Stage errorWindow = new Stage();
            errorWindow.initModality(Modality.APPLICATION_MODAL);
            errorWindow.setTitle("Error");
            Parent errorLayout = FXMLLoader.load(new URL("file:src/editor/ErrorBox.fxml"), new MyResources("Error", "Password is incorrect"));
            Scene scene = new Scene(errorLayout);
            errorWindow.setScene(scene);
            errorWindow.showAndWait();
            EditorController.receivedPassword = passText.getText();
        }
    }

    /**
     * When the cancel key is pressed,this function activated
     *
     */
    public void cancel() {
        EditorController.sign = true;
        //eger arayuzden editorController class,na bir sey return edebilirsek 
        //bu sign silinecek
        ((Stage) (passScene.getScene().getWindow())).close();
    }

}
