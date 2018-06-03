/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import application.data.GenericFXController;
import com.fasterxml.jackson.databind.ObjectMapper;
import tdo.user.UserTDO;
import tdo.pack.PackTDO;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import rest.dto.component.ComponentData;
import rest.dto.pack.PackContent;
import rest.dto.pack.PackServerResponse;
import tdo.component.ComponentTDO;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class PackController extends GenericFXController implements Initializable {

    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<PackTDO> tablePack;
    @FXML
    private TableColumn<PackTDO,String> columnPack;
    @FXML
    private TableView<ComponentTDO> tableApplication;
    @FXML
    private TableColumn<ComponentTDO, String> columnApplication;
    @FXML
    private TableView<UserTDO> tableUtilisateur;
    @FXML
    private TableColumn<UserTDO, String> columnUser;
  
    private final String PACK_FORM = "/fxml/pack/PackForm.fxml";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            getAllPacks();
            initTables();
        } catch (UnirestException | IOException ex) {
            Logger.getLogger(PackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void doSearch(KeyEvent event) {
        initTables();
        Predicate<PackTDO> searchPackPredicate = pack->{
            return !pack.getPackName().toLowerCase().contains(searchField.getText().toLowerCase());
        };
        tablePack.getItems().removeAll(tablePack.getItems().filtered(searchPackPredicate));
    }

    @FXML
    private void showData(ActionEvent event) {
    }

    
    
    @FXML
    private void showAdd(ActionEvent event) {
        PackFormController.setMode(ADD_MODE, Long.valueOf(0));
        showModalForm(event, PACK_FORM);
    }

    @FXML
    private void showEdit(ActionEvent event) {
        PackFormController.setMode(EDIT_MODE, tablePack.getSelectionModel().getSelectedItem().getPackID());
        showModalForm(event, PACK_FORM);
    }

    @FXML
    private void doDelete(ActionEvent event) throws UnirestException {
        final PackTDO selectedPack = tablePack.getSelectionModel().getSelectedItem();
        if(showConfirmAlert(event, "Voulez vous vraiment suprimer le pack !! : "+selectedPack.getPackName())){
            HttpResponse<String> asString = Unirest.delete(PACK_SERVER_URL).queryString("pack_id",selectedPack.getPackID()).asString();
            if(asString.getStatus() == 200){
                tablePack.getItems().remove(selectedPack);
            }
        }
        
    }

    private void initTables() {
        tableApplication.getItems().clear();
        tablePack.getItems().clear();
        tableUtilisateur.getItems().clear();
        
        packs.stream().forEach(pack->{
            final PackTDO packTDO = new PackTDO();
            packTDO.setPackID(pack.getId());
            packTDO.setPackName(pack.getPackName());
            packTDO.setPack(pack);
            tablePack.getItems().add(packTDO);
        });
        
        tablePack.getSelectionModel().selectedItemProperty().addListener((obs,oldPack,newPack) -> {
            if(newPack != null){
                tableApplication.getItems().clear();
                newPack.getPack().getMngComposants().stream().forEach(application -> {
                    Predicate<? super ComponentData> appNamePredicate = appData ->{
                        return appData.getCmpAttrLabel().equals("appName");
                    };
                
                    String appName = application.getCmpDatas().stream().filter(appNamePredicate).findFirst().get().getCmpAttrValue();
                    final ComponentTDO app = new ComponentTDO();
                    app.setComponentName(appName);
                    tableApplication.getItems().add(app);
                });
                tableUtilisateur.getItems().clear();
                try {
                    HttpResponse<String> asString = Unirest.get(USERS_SERVER_URL+"/pack/{pack_id}").routeParam("pack_id", newPack.getPackID()+"").asString();
                    System.out.println(asString.getBody());
                    if(asString.getStatus() == 200){
                        List<LightUser> lightUsers = new ObjectMapper().readValue(asString.getBody(), PackUsersResponse.class).getContent();
                        lightUsers.stream().forEach(user -> {
                            final UserTDO userTDO = new UserTDO();
                            userTDO.setUsername(user.getUsername());
                            userTDO.setUserID(user.getUserID());
                            tableUtilisateur.getItems().add(userTDO);
                        });
                        
                        
                    }
                } catch (UnirestException | IOException ex) {
                    Logger.getLogger(PackController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        columnApplication.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        columnPack.setCellValueFactory(new PropertyValueFactory<>("packName"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    private List<PackContent> packs;
    private void getAllPacks() throws UnirestException, IOException {
        HttpResponse<String> asString = Unirest.get(PACK_SERVER_URL).asString();
        System.out.println(asString.getBody());
        if(asString.getStatus() == 200){
            System.out.println(asString.getBody());
            final ObjectMapper objectMapper = new ObjectMapper();
            PackServerResponse packServerResponse = objectMapper.readValue(asString.getBody(), PackServerResponse.class);
            packs = packServerResponse.getPackContent();
            
        }
    }
    
}
