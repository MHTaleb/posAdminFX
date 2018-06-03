package easypos.easyposserveradministrator;

import application.data.ApplicationGlobalData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.JSONObject;
import pojo.LoginPojo;
import tools.Crypto;

public class FXMLController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    @FXML
    private void doConnect(ActionEvent event) throws UnirestException, NoSuchAlgorithmException, IOException {

        String originalInput = username.getText() + ":" + password.getText();
        String value = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println("value  = " + value);
        HttpResponse<String> response = Unirest.get("https://localhost:8443/logins/login/?us_username=" + username.getText() + "&us_pwdusr=" + Crypto.getSha(password.getText()))
                .header("authorization", "Basic " + value)
                .header("cache-control", "no-cache")
                .asString();
        System.out.println("response : " + response.getBody());
        System.out.println("response : " + response.getHeaders().entrySet());
        String jsonString = response.getBody();
        JSONObject jsonObj = new JSONObject(jsonString);

        for (Iterator iterator = jsonObj.keys(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object valueObject = jsonObj.get(key);
            System.out.println(key + " = " + valueObject);
        }
        String message = jsonObj.getString("message");
        JSONObject body = jsonObj.getJSONObject("content");

        if (message.equals("success") //&& !body.isNull("us_usrint") 
                ) {
            username.setText("");
            password.setText("");
            LoginPojo readValue = null;
            try {
                readValue = new ObjectMapper().readValue(body.toString(), LoginPojo.class);
                System.out.println(readValue);
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                List<String> cookies = response.getHeaders().get("Set-Cookie");
                for (String cooky : cookies) {
                    System.out.println(cooky);
                    Unirest.setDefaultHeader("Cookie", cooky);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Unirest.setDefaultHeader("accept", "application/json");

            ApplicationGlobalData.setLoginPojo(readValue);
            
            Stage stage = new Stage();
            
             Parent root = FXMLLoader.load(getClass().getResource("/fxml/board.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/board.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.initModality(Modality.APPLICATION_MODAL);
            final Stage parent = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.initOwner(parent);
        parent.hide();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                @Override
                public void handle(WindowEvent event) {
                    parent.show();
                    try {
                        HttpResponse<String> asString = Unirest.get("https://localhost:8443/logins/logout").asString();
                        Unirest.clearDefaultHeaders();
                        System.out.println(asString.getBody());
                    } catch (UnirestException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                }
            });
        stage.show();
            System.out.println("login success");
            System.out.println("as user id " + body.getLong("us_usrint"));
            
            
        }

    }

    @FXML
    private void resetForm(ActionEvent event) {
        username.setText("");
        password.setText("");
    }
}
