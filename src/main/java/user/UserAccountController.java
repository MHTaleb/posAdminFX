/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import application.data.GenericFXController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import rest.dto.component.ComponentContent;
import rest.dto.component.ComponentData;
import rest.dto.pack.PackContent;
import tdo.component.ComponentTDO;
import tdo.pack.PackTDO;
import tdo.user.UserTDO;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class UserAccountController extends GenericFXController implements Initializable {

    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<PackTDO> tablePack;
    @FXML
    private TableColumn<PackTDO, String> columnPack;
    @FXML
    private TableView<ComponentTDO> tableApplication;
    @FXML
    private TableColumn<ComponentTDO, String> columnApplication;
    @FXML
    private TableView<UserTDO> tableUtilisateur;
    @FXML
    private TableColumn<UserTDO, String> columnUser;
    @FXML
    private TableColumn<UserTDO, String> colAcl;

    private ObservableList<UserTDO> usersDtoItems;

    private final String USER_FORM = "/fxml/user/UserAccountForm.fxml";
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            initDatas();
            initTables();
        } catch (UnirestException | IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    @FXML
    private void doSearch(KeyEvent event) {
        tableUtilisateur.getItems().clear();
        tablePack.getItems().clear();
        tableApplication.getItems().clear();
        Predicate<? super UserTDO> searchPredicate = user -> {
            return user.getUsername().toLowerCase().contains(searchField.getText().toLowerCase());
        };
        usersDtoItems.stream().filter(searchPredicate).forEach(user -> {
            tableUtilisateur.getItems().add(user);
        });
        if (tableUtilisateur.getItems().size() > 0) {
            tableUtilisateur.getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void showData(ActionEvent event) {
    }

    @FXML
    private void showAdd(ActionEvent event) {
        UserAccountFormController.setMode(ADD_MODE, Long.MIN_VALUE);
        showModalForm(event, USER_FORM);
        
    }

    @FXML
    private void showEdit(ActionEvent event) {
        UserAccountFormController.setMode(EDIT_MODE, tableUtilisateur.getSelectionModel().getSelectedItem().getUserID());
        showModalForm(event, USER_FORM);
    }

    @FXML
    private void doDelete(ActionEvent event) throws UnirestException {
        final UserTDO selectedItem = tableUtilisateur.getSelectionModel().getSelectedItem();
        long userID = selectedItem.getUserID();
        if (showConfirmAlert(event, "voulea vous supprimer : " + selectedItem.getUsername())) {
            int status = Unirest.delete(USERS_SERVER_URL).queryString("id", userID).asString().getStatus();
            if (status == 200) {
                usersDtoItems.remove(selectedItem);
                tableUtilisateur.getItems().remove(selectedItem);
            }
        }
    }

    private void initDatas() throws UnirestException, IOException {
        HttpResponse<String> asString = Unirest.get(USERS_SERVER_URL).asString();
        usersDtoItems = FXCollections.observableArrayList();
        System.out.println(asString.getBody());
        if (asString.getStatus() == 200) {
            List<UserDTO> content = new ObjectMapper().readValue(asString.getBody(), UsersServerResponseList.class).getContent();
            System.out.println(content);
            content.stream().forEach(user -> {
                System.out.println("user = " + user);
                final UserTDO userTDO = new UserTDO();
                userTDO.setUserID(user.getUserID());
                userTDO.setAcl(user.getAclName());
                userTDO.setUsername(user.getUsername());
                userTDO.setUserDTO(user);
                usersDtoItems.add(userTDO);
            });
            System.out.println("user = " + usersDtoItems);
        }

    }

    private void initTables() {
        tableUtilisateur.getItems().clear();
        tablePack.getItems().clear();
        tableApplication.getItems().clear();
        usersDtoItems.stream().forEach(user -> {
            System.out.println("user = " + user);
            tableUtilisateur.getItems().add(user);
        });
        tableUtilisateur.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> {
            if (newItem != null) {
                tablePack.getItems().clear();
                newItem.getUserDTO().getPacks().stream().forEach(pack -> {
                    final PackTDO packTDO = new PackTDO();
                    List<ComponentContent> apps = new ArrayList();
                    pack.getApps().stream().forEach(app -> {
                        List<ComponentData> cmpDatas = new ArrayList();
                        app.getCmpDatas().stream().forEach(data -> {
                            ComponentData cmpData = new ComponentData(data.getId(), data.getCmpAttrLabel(), data.getCmpAttrValue(), data.getCmpAttrCode());
                            cmpDatas.add(cmpData);
                        });
                        apps.add(new ComponentContent(app.getId(), app.getCmpType(), null, null, cmpDatas));
                    });
                    packTDO.setPack(new PackContent(pack.getPackID(), pack.getPackName(), apps));
                    packTDO.setPackName(pack.getPackName());
                    tablePack.getItems().add(packTDO);
                });
            }
        });
        tablePack.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> {
            if (newItem != null) {
                tableApplication.getItems().clear();
                newItem.getPack().getMngComposants().stream().forEach(app -> {
                    ComponentTDO element = new ComponentTDO();
                    element.setComponentID(app.getId());
                    Predicate<? super ComponentData> appNamePredicate = appData -> {
                        return appData.getCmpAttrCode().equals("appName");
                    };
                    element.setComponentName(app.getCmpDatas().stream().filter(appNamePredicate).findFirst().get().getCmpAttrValue());
                    element.setComponent(app);
                    tableApplication.getItems().add(element);
                });
            }
        });
        colAcl.setCellValueFactory(new PropertyValueFactory("acl"));
        columnApplication.setCellValueFactory(new PropertyValueFactory("componentName"));
        columnPack.setCellValueFactory(new PropertyValueFactory("packName"));
        columnUser.setCellValueFactory(new PropertyValueFactory("username"));

    }

}
