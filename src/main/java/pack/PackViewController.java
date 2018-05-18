/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class PackViewController implements Initializable {

    @FXML
    private TableView<?> tableLangue;
    @FXML
    private TableColumn<?, ?> columnLangue;
    @FXML
    private TableView<?> tableLangue1;
    @FXML
    private TableColumn<?, ?> columnLangue1;
    @FXML
    private JFXTextField titleACL;
    @FXML
    private HBox checkBoxContainer;
    @FXML
    private JFXCheckBox admin;
    @FXML
    private JFXCheckBox testeur;
    @FXML
    private JFXCheckBox client;
    @FXML
    private JFXCheckBox autre;
    @FXML
    private JFXTextField searchField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doAdd(ActionEvent event) {
    }

    @FXML
    private void doUpdate(ActionEvent event) {
    }

    @FXML
    private void doDelete(ActionEvent event) {
    }

    @FXML
    private void doSearch(KeyEvent event) {
    }
    
}
