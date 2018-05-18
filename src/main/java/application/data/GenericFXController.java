/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author taleb
 */
public  class GenericFXController {
    private final FXMLLoader fXMLLoader = new FXMLLoader();

    private final List<Control> requiredControles = new ArrayList();
    
    public void addRequiredElement(Control control){
        requiredControles.add(control);
    }
    
    public boolean validate(){
        boolean result  = true;
        if (!requiredControles.stream().filter((control) -> (control instanceof  TextInputControl)).map((control) -> (TextInputControl) control).noneMatch((inputControl) -> (inputControl.getText().isEmpty()))) {
            return false;
        }
        return result;
    }
    
    public void showForm(final String formFXPath) {
        try {
            Stage stage = new Stage();
            Parent root = fXMLLoader.load(getClass().getResource(formFXPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showModalForm(final Event event, final String formFXPath) {
        Stage stage = new Stage();
        try {

            Parent root = fXMLLoader.load(getClass().getResource(formFXPath));
            Scene scene = new Scene(root);
            

            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            final Stage parent = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(parent);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showForm(final VBox container, final String formFXPath) throws IOException {
        VBox languageForm = fXMLLoader.load(getClass().getResource(formFXPath));

        container.getChildren().clear();
        final HBox form = (HBox) languageForm.getChildren().get(0);
        container.getChildren().add(form);
        container.setBackground(form.getBackground());
        languageForm.autosize();
        container.setVgrow(form, Priority.ALWAYS);
        container.autosize();
    }

    public void showErrorAlert(ActionEvent event, final String messageError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(((Node) event.getSource()).getScene().getWindow());
        alert.setContentText(messageError);
        alert.showAndWait();
    }

    public void showInformAlert(ActionEvent event, final String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(((Node) event.getSource()).getScene().getWindow());
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean showConfirmAlert(ActionEvent event, final String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(((Node) event.getSource()).getScene().getWindow());
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.setContentText(message);
        alert.showAndWait();
        return alert.getResult().getText().equals(ButtonType.YES.getText());
    }

}
