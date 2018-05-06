/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import application.data.ApplicationGlobalData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class LanguageViewController implements Initializable {

    @FXML
    private TableView<LangueDto> tableLangue;
    @FXML
    private TableColumn<LangueDto, String> columnLangue;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextField searchField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        initTables();
        try {
            populateTablesDataFromRestService();
        } catch (UnirestException | IOException ex) {
            Logger.getLogger(LanguageViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void addLangue(ActionEvent event) throws UnirestException, UnsupportedEncodingException{
        String codeLangue = code.getText().toUpperCase();
        final UniqueContraintCheck uniqueContraintCheck = new UniqueContraintCheck(codeLangue);
        
        langages.stream().forEach(uniqueContraintCheck);
        
        if(!codeLangue.isEmpty() && !uniqueContraintCheck.isFound()){
            System.out.println(servicePath);
            HttpResponse<String> response = Unirest.post(URLEncoder.encode("https://localhost:8443/lang?code="+codeLangue, "UTF-8"))
                    .header("accept", "application/json")
                    .asString();
            String langObject = new JSONObject(response.getBody()).getJSONObject("content").toString();
            System.out.println("langobject" + langObject);
            LangueDto addedLangue;
            try {
                addedLangue = new ObjectMapper().readValue(langObject, LangueDto.class);
                System.out.println(addedLangue.getCode());
                tableLangue.getItems().add(addedLangue);
                langages.add(addedLangue);
            } catch (IOException ex) {
                Logger.getLogger(LanguageViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void doDelete(ActionEvent event) throws UnirestException, UnsupportedEncodingException {
        final LangueDto selectedItem = tableLangue.getSelectionModel().getSelectedItem();
        
        long id = selectedItem.getId();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Veux tu confirmer la suppression de la langue sellectionner", ButtonType.OK,ButtonType.CANCEL);
        confirm.showAndWait();
        System.out.println(confirm.getResult().getText());
        if(confirm.getResult().getText().equals(ButtonType.OK.getText())){
            HttpResponse<String> asString = Unirest.delete(URLEncoder.encode("https://localhost:8443/lang?id="+id, "UTF-8")).asString();
            System.out.println(asString.getBody());
            tableLangue.getItems().remove(selectedItem);
            langages.remove(selectedItem);
            
        }
    }

    @FXML
    private void doUpdate(ActionEvent event) throws UnirestException {
        
        final LangueDto selectedItem = tableLangue.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "aucune entrée n'as ete selectionner ", ButtonType.OK);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Node)event.getSource()).getScene().getWindow());
            alert.show();
            tableLangue.requestFocus();
            return;
        }
        
        if(code.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "champ code vide invalide ", ButtonType.OK);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Node)event.getSource()).getScene().getWindow());
            alert.show();
            code.requestFocus();
            return;
        }
        
        final UniqueContraintCheck uniqueContraintCheck = new UniqueContraintCheck(code.getText());
        
        langages.stream().forEach(uniqueContraintCheck);
        if (uniqueContraintCheck.isFound()) {
             Alert alert = new Alert(Alert.AlertType.ERROR, "modification avec ce code est impossible car il existe deja", ButtonType.OK);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Node)event.getSource()).getScene().getWindow());
            alert.show();
            code.requestFocus();
            return;
        }
  
        HttpResponse<String> asString = Unirest.put("https://localhost:8443/lang")//?id="+selectedItem.getId()+"&code="+code.getText())
                .field("id", selectedItem.getId())
                .field("code", code.getText())
                .asString();
        if(asString.getStatus() == 200){
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "modification avec succes", ButtonType.OK);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Node)event.getSource()).getScene().getWindow());
            alert.show();
            
            tableLangue.getItems().get(tableLangue.getItems().indexOf(selectedItem)).setCode(code.getText());
            langages.get(langages.indexOf(selectedItem)).setCode(code.getText());
            selectedItem.setCode(code.getText());
            
            
            
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur serveur", ButtonType.OK);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(((Node)event.getSource()).getScene().getWindow());
            alert.show();
            
            
        }
    }

    private void initTables() {
        
        columnLangue.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        ObservableList<LangueDto> langueList = FXCollections.observableArrayList();
        tableLangue.setItems(langueList);
    }
    private List<LangueDto> langages;
    private final String servicePath =ApplicationGlobalData.SERVER_URL+"/lang";

    private void populateTablesDataFromRestService() throws UnirestException, IOException {
        HttpResponse<String> asString = Unirest.get(servicePath).asString();
        System.out.println(asString.getStatus()+" statuc code \n"+asString.getBody());
        if(asString.getStatus()==200){
            String serverBodyResponse = asString.getBody();
            JSONObject jsonObject = new JSONObject(serverBodyResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("content");
            final ObjectMapper objectMapper = new ObjectMapper();
            langages = objectMapper.readValue(jsonArray.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, LangueDto.class));
            tableLangue.getItems().clear();
            tableLangue.getItems().addAll(langages);
                    
        }
    }

    @FXML
    private void doSearch(KeyEvent event) {
        String searchValue = searchField.getText();
        if(searchValue.isEmpty()){
            tableLangue.getItems().clear();
            tableLangue.getItems().addAll(langages);
        }else{
            List<LangueDto> searchList = new ArrayList();
            Predicate<? super LangueDto> prdct = lang-> {
                return lang.codeProperty().getValue().contains(searchValue);
            };
            searchList.addAll(langages.stream().filter(prdct).collect(Collectors.toList()));
            tableLangue.getItems().clear();
            tableLangue.getItems().addAll(searchList);
            
        }
    }

    private static class UniqueContraintCheck implements Consumer<LangueDto> {

        private final String codeLangue;

        public UniqueContraintCheck(String codeLangue) {
            this.codeLangue = codeLangue;
        }
        
        private boolean found = false;

        public boolean isFound() {
            return found;
        }
        
        
        
        @Override
        public void accept(LangueDto lang) {
            if(!found)if(lang.codeProperty().getValue().equals(codeLangue)){
                Alert alert = new Alert(Alert.AlertType.ERROR, "interdit de dupliquer la meme langue code langue deja existant", ButtonType.OK);
                alert.show();
                found = true;
            }
        }
    }
     
    
}
