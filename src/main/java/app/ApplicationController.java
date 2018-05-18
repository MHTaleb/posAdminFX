/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import application.data.ApplicationGlobalData;
import application.data.GenericFXController;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class ApplicationController extends GenericFXController implements Initializable {

    public static boolean doSycnh;
    
    @FXML
    private TableColumn<ApplicationTDO, String> columnAppName;
    @FXML
    private JFXTextField searchField;

    private final String serviceURL = ApplicationGlobalData.SERVER_URL+"/applications";
    @FXML
    private TableView<ApplicationTDO> tableApplication;
    @FXML
    private TableView<AppDataAttTDO> tableApplicationDetail;
    @FXML
    private TableColumn<AppDataAttTDO, String> columnCodeAttribut;
    @FXML
    private TableColumn<AppDataAttTDO, String> columnTitreAttribut;
    @FXML
    private TableColumn<AppDataAttTDO, String> columnValeur;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            initTable();
            getAllApplications();
        } catch (UnirestException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    



    @FXML
    private void doSearch(KeyEvent event) {
    }

    private void initTable() {
       
    }

    private void getAllApplications() throws UnirestException {
        
        HttpResponse<String> asString = Unirest.get(serviceURL).asString();
        System.out.println(asString.getBody());
        
    }

    @FXML
    private void showData(ActionEvent event) {
    }

    @FXML
    private void showAdd(ActionEvent event) {
        ApplicationFormAddController.setMode(ApplicationFormAddController.ADD_MODE, Long.valueOf(-1));
        showModalForm(event, "/fxml/application/ApplicationForm.fxml");
    }

    @FXML
    private void showEdit(ActionEvent event) {
       // ApplicationFormAddController.setMode(ApplicationFormAddController.EDIT_MODE, Long.valueOf(tableApplication.getSelectionModel().getSelectedItem()));
        showModalForm(event, "/fxml/application/ApplicationForm.fxml");
    }

    @FXML
    private void doDelete(ActionEvent event) {
    }

   

   
    
}
