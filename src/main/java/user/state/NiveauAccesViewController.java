/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.state;

import application.data.GenericFXController;
import application.data.ApplicationGlobalData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import language.LangueDto;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class NiveauAccesViewController extends GenericFXController implements Initializable {

    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<NiveauAccesDTO> tableLangue;
    @FXML
    private TableColumn<NiveauAccesDTO, String> columnLangue;
    @FXML
    private JFXTextField titleACL;
    @FXML
    private TableView<RoleDTO> tableLangue1;
    @FXML
    private TableColumn<RoleDTO, String> columnLangue1;
    @FXML
    private JFXCheckBox admin;
    @FXML
    private JFXCheckBox testeur;
    @FXML
    private JFXCheckBox client;
    @FXML
    private JFXCheckBox autre;
    @FXML
    private HBox checkBoxContainer;

    // Use Java Collections to create the List.
    Map<String, JFXCheckBox> checkBoxes = new HashMap<>();

    // Now add observability by wrapping it with ObservableList.
    ObservableMap<String, JFXCheckBox> observableCheckBoxes = FXCollections.observableMap(checkBoxes);

    private final String ACL_URL = ApplicationGlobalData.SERVER_URL + "/acl";
    private final String ROLE_URL = ApplicationGlobalData.SERVER_URL + "/roles";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initSearch();
            setupTable();
            getAllAcl();
            getAllRoles();
        } catch (UnirestException ex) {
            Logger.getLogger(NiveauAccesViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NiveauAccesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void doSearch(KeyEvent event) {
    }

    @FXML
    private void doAdd(ActionEvent event) throws UnirestException, IOException {
        if (checkTitleNotEmpty(event)) {
            return;
        }
        String title = titleACL.getText();
        Predicate<? super JFXCheckBox> selectedCheckboxes = checkbox -> {
            return checkbox.isSelected();
        };
        List<JFXCheckBox> selectedCheckBoxesList = observableCheckBoxes.values().stream().filter(selectedCheckboxes).collect(Collectors.toList());
        final List<String> roles = new ArrayList<>();
        selectedCheckBoxesList.stream().forEach(checkbox -> {
            roles.add(checkbox.getText());
        });

        HttpResponse<String> asString = Unirest.post(ACL_URL)
                .header("accept", "application/json")
                .field("aclTitle", title)
                .field("roles", roles)
                .asString();

        System.out.println(asString.getStatus());
        System.out.println(asString.getHeaders().entrySet());
        if (asString.getStatus() == 200) {
            String content = asString.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            ACLContent aclContent = objectMapper.readValue(content, ACLOneObjectServerAnswer.class).getContent();
            final NiveauAccesDTO niveauAccesDTO = new NiveauAccesDTO();
            niveauAccesDTO.setId(aclContent.getId());
            niveauAccesDTO.setRoleTitle(aclContent.getAclTitle());
            niveauAccesDTO.setRoles(FXCollections.observableArrayList(aclContent.getRoles()));
            tableLangue.getItems().add(niveauAccesDTO);
        }
    }

    public boolean checkTitleNotEmpty(ActionEvent event) {
        if (titleACL.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(((Node) event.getSource()).getScene().getWindow());
            alert.setContentText("Veuillez remplir le champ");
            alert.showAndWait();
            titleACL.requestFocus();
            return true;
        }
        return false;
    }

    @FXML
    private void doUpdate(ActionEvent event) throws UnirestException {
        NiveauAccesDTO selectedItem = tableLangue.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            long id = selectedItem.getId();
            if (checkTitleNotEmpty(event)) {
                return;
            }
            String title = titleACL.getText();
            Predicate<? super JFXCheckBox> selectedCheckboxes = checkbox -> {
                return checkbox.isSelected();
            };

            List<JFXCheckBox> selectedCheckBoxesList = observableCheckBoxes.values().stream().filter(selectedCheckboxes).collect(Collectors.toList());
            final List<String> roles = new ArrayList<>();
            selectedCheckBoxesList.stream().forEach(checkbox -> {
                roles.add(checkbox.getText());
            });

            HttpResponse<String> asString = Unirest.put(ACL_URL)
                    .header("accept", "application/json")
                    .field("id",id)
                    .field("aclTitle", title)
                    .field("roles", roles)
                    .asString();
            if(asString.getStatus() == 200){
               tableLangue.getSelectionModel().getSelectedItem().setRoleTitle(title);
               tableLangue.getSelectionModel().getSelectedItem().setRoles(FXCollections.observableArrayList(roles));
               tableLangue.getSelectionModel().selectBelowCell();
               tableLangue.getSelectionModel().select(selectedItem);
            }else{
            final String messageError = "Erreur de Modification "+asString.getStatusText()+ "code error "+asString.getStatus();
                showErrorAlert(event, messageError);
            }
            
        }
    }


    @FXML
    private void doDelete(ActionEvent event) throws UnirestException {
         NiveauAccesDTO selectedItem = tableLangue.getSelectionModel().getSelectedItem();
         
        if (selectedItem != null && showConfirmAlert(event, "Voulez vous suprimmer "+selectedItem.getRoleTitle())) {
            long id = selectedItem.getId();
             HttpResponse<String> asString = Unirest.delete(ACL_URL)
                     .header("accept", "application/json")
                     .queryString("id", id).asString();
             
             if(asString.getStatus() == 200){
                 tableLangue.getItems().remove(selectedItem);
                 tableLangue.getSelectionModel().selectFirst();
             }
                    
        }
        
    }

    
    List<NiveauAccesDTO> acls;
    private void getAllAcl() throws UnirestException, IOException {
        acls = new ArrayList();
        HttpResponse<String> asJsonHttpResponse = Unirest.get(ACL_URL).asString();
        String content = asJsonHttpResponse.getBody();
        System.out.println("content" + content);
        if (asJsonHttpResponse.getStatus() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            ACLRequestAnswer serverAnswer = objectMapper.readValue(content, ACLRequestAnswer.class);
            serverAnswer.getContent().stream().forEach(aclMember -> {
                System.out.println(aclMember);
                final NiveauAccesDTO niveauAccesDTO = new NiveauAccesDTO();
                niveauAccesDTO.setId(aclMember.getId());
                niveauAccesDTO.setRoleTitle(aclMember.getAclTitle());
                niveauAccesDTO.setRoles(FXCollections.observableArrayList(aclMember.getRoles()));
                acls.add(niveauAccesDTO);
                tableLangue.getItems().add(niveauAccesDTO);
            });
            tableLangue.getSelectionModel().selectFirst();
        }

    }

    private void getAllRoles() throws UnirestException, IOException {
        HttpResponse<String> asJsonHttpResponse = Unirest.get(ROLE_URL).asString();
        String content = asJsonHttpResponse.getBody();
        System.out.println("content" + content);
        if (asJsonHttpResponse.getStatus() == 200) {

            ObjectMapper objectMapper = new ObjectMapper();
            ServerRolesResponse serverAnswer = objectMapper.readValue(content, ServerRolesResponse.class);
            checkBoxContainer.getChildren().clear();
            observableCheckBoxes.clear();
            serverAnswer.getRoles().stream().forEach(role -> {
                final JFXCheckBox jfxCheckBox = new JFXCheckBox(role);
                observableCheckBoxes.put(role, jfxCheckBox);
                checkBoxContainer.getChildren().add(jfxCheckBox);
            });

        }
    }

    private void setupTable() {
        tableLangue.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tableLangue1.getItems().clear();
                newSelection.getRoles().stream().forEach(role -> {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRole(role);
                    tableLangue1.getItems().add(roleDTO);
                });
            }
        });
        columnLangue.setCellValueFactory(new PropertyValueFactory<>("roleTitle"));
        columnLangue1.setCellValueFactory(new PropertyValueFactory<>("role"));

    }

     private void initSearch() {
         searchField.setOnKeyReleased(event -> {
             String searchValue = searchField.getText();
        if(searchValue.isEmpty()){
            tableLangue.getItems().clear();
            tableLangue.getItems().addAll(acls);
        }else{
            List<NiveauAccesDTO> searchList = new ArrayList();
            Predicate<? super NiveauAccesDTO> prdct = acl-> {
                return acl.roleTitleProperty().getValue().toLowerCase().contains(searchValue.toLowerCase());
            };
            searchList.addAll(acls.stream().filter(prdct).collect(Collectors.toList()));
            tableLangue.getItems().clear();
            tableLangue.getItems().addAll(searchList);
            
        }
             
         });
     }
    
}
