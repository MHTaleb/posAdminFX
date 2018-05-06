/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import application.data.ApplicationGlobalData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class BoardController implements Initializable {

    @FXML
    private Label currentUser;
    @FXML
    private VBox container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        currentUser.setText(ApplicationGlobalData.getLoginPojo().getUs_nomusr()+" "+ApplicationGlobalData.getLoginPojo().getUs_prnusr());
    }    

    @FXML
    private void showLangueForm(ActionEvent event) throws IOException {
        
        VBox languageForm = FXMLLoader.load(getClass().getResource("/fxml/LanguageView.fxml"));
        container.getChildren().clear();
        final HBox form = (HBox) languageForm.getChildren().get(0);
        container.getChildren().add(form);
        container.setBackground(languageForm.getBackground());
      form.setMinHeight(container.getHeight());
      form.setMinWidth(container.getWidth());
      form.setMaxHeight(container.getHeight());
      form.setMaxWidth(container.getWidth());
      form.prefWidthProperty().bind(container.widthProperty());
      form.prefHeightProperty().bind(container.heightProperty());
        form.autosize();
        container.autosize();
        
        
        
    }
    
}
