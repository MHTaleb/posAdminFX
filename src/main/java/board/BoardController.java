/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import application.data.ApplicationGlobalData;
import application.data.GenericFXController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class BoardController extends GenericFXController implements Initializable {

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
        final String formFXPath = "/fxml/LanguageView.fxml";    
        showForm(container , formFXPath);
    }

   

    @FXML
    private void showNiveauAccess(ActionEvent event)throws IOException {
        final String formFXPath = "/fxml/NiveauAccesView.fxml";    
        showForm(container ,formFXPath);
        
    }

    @FXML
    private void showPack(ActionEvent event) throws IOException {
        final String formFXPath = "/fxml/pack/PackMain.fxml";    
        showForm(container ,formFXPath);
    }

    @FXML
    private void showUserType(ActionEvent event) {
    }

    @FXML
    private void showFormUser(ActionEvent event) throws IOException {
        
        final String formFXPath = "/fxml/user/UserAccountMain.fxml";    
        showForm(container ,formFXPath);
    }

    @FXML
    private void showFonction(ActionEvent event) throws IOException {
        final String formFXPath = "/fxml/fonction/FonctionMain.fxml";    
        showForm(container ,formFXPath);
    }

    @FXML
    private void showMenu(ActionEvent event) throws IOException {
        final String formFXPath = "/fxml/menu/MenuMain.fxml";    
        showForm(container ,formFXPath);
    }

    @FXML
    private void showApplication(ActionEvent event) throws IOException {
        final String formFXPath = "/fxml/application/Application.fxml";    
        showForm(container ,formFXPath);
    }

    
}
