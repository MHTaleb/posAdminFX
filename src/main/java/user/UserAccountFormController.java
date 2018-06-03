/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import acl.ACLContent;
import acl.ACLRequestAnswer;
import application.data.GenericFXController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import language.Lang;
import language.LanguagesServerResponseList;
import rest.dto.pack.PackServerResponse;
import tdo.pack.PackTDO;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class UserAccountFormController extends GenericFXController implements Initializable {

    public static boolean doSynch;

    private static boolean mode;
    private static Long user_id;

    @FXML
    private TableColumn<PackTDO, Boolean> colChecked;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField passwordConfirm;
    @FXML
    private JFXComboBox<ACLCDO> droitAccesBox;
    @FXML
    private JFXComboBox<LangueCDO> langueBox;
    @FXML
    private JFXCheckBox userStateCheck;
    @FXML
    private JFXTextField codeExterne;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXDatePicker dateDebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private ImageView resetButton;
    @FXML
    private JFXTextField rechercherPack;
    @FXML
    private TableView<PackTDO> tablePack;
    @FXML
    private TableColumn<PackTDO, String> colPack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            initData();
            initTables();

            if (EDIT_MODE == mode) {
                setupEditForm();
            }
        } catch (UnirestException | IOException ex) {
            Logger.getLogger(UserAccountFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void doSearchParent(KeyEvent event) {
    }

    @FXML
    private void doCommit(ActionEvent event) {
    }

    public static void setMode(boolean mode, Long user_id) {
        UserAccountFormController.mode = mode;
        UserAccountFormController.user_id = user_id;
        System.out.println("with menu id = " + user_id);
    }

    private final ObservableList<PackTDO> packsList = FXCollections.observableArrayList();
    private final ObservableList<ACLCDO> aclsList = FXCollections.observableArrayList();
    private final ObservableList<LangueCDO> langueList = FXCollections.observableArrayList();

    private void initTables() {
        tablePack.getItems().addAll(packsList);
        droitAccesBox.getItems().addAll(aclsList);
        langueBox.getItems().addAll(langueList);
    }

    private void initData() throws UnirestException, IOException {

        HttpResponse<String> stringResponse = Unirest.get(PACK_SERVER_URL).asString();
        final ObjectMapper objectMapper = new ObjectMapper();
        if (stringResponse.getStatus() == 200) {
            PackServerResponse packServerResponse = objectMapper.readValue(stringResponse.getBody(), PackServerResponse.class);
            packServerResponse.getPackContent().stream().forEach(pack -> {
                final PackTDO packTDO = new PackTDO();
                packTDO.setCheckedPack(false);
                packTDO.setPack(pack);
                packTDO.setPackID(pack.getId());
                packTDO.setPackName(pack.getPackName());
                packsList.add(packTDO);
            });
        }

        HttpResponse<String> stringLangueResponse = Unirest.get(LANGUE_SERVICE_URL).asString();
        if (stringLangueResponse.getStatus() == 200) {
            System.out.println("stringLangueResponse.getBody()   " + stringLangueResponse.getBody());
            List<Lang> langs = objectMapper.readValue(stringLangueResponse.getBody(), LanguagesServerResponseList.class).getLangs();
            langs.stream().forEach(lang -> {
                final LangueCDO langueCDO = new LangueCDO();
                langueCDO.setId(lang.getId());
                langueCDO.setLangue(lang.getCode());
                langueList.add(langueCDO);
            });
        }

        HttpResponse<String> AclServerResponseString = Unirest.get(ACL_URL).asString();
        if (AclServerResponseString.getStatus() == 200) {
            System.out.println("stringLangueResponse.getBody()   " + AclServerResponseString.getBody());
            List<ACLContent> aclcs = objectMapper.readValue(AclServerResponseString.getBody(), ACLRequestAnswer.class).getContent();
            aclcs.stream().forEach(acl -> {
                final ACLCDO aclcdo = new ACLCDO();
                aclcdo.setAclTitle(acl.getAclTitle());
                aclcdo.setId(acl.getId());
                aclcdo.setRoles(FXCollections.observableArrayList(acl.getRoles()));
                aclsList.add(aclcdo);
            });

        }

    }

    private void setupEditForm() throws UnirestException, IOException {
        HttpResponse<String> editable_User = Unirest.get(USERS_SERVER_URL + "/{user_id}").routeParam("user_id", "" + user_id).asString();
        if (editable_User.getStatus() == 200) {
            OneUserDTO oneUser = new ObjectMapper().readValue(editable_User.getBody(), OneUserServerResponse.class).getContent();
            username.setText(oneUser.getUtilisateur());
            codeExterne.setText(oneUser.getCodeExterne());
            dateDebut.setValue(LocalDate.parse(oneUser.getDateDebut(),DateTimeFormatter.ofPattern("d/MM/uuuu")));
            dateDebut.requestFocus();
            dateDebut.getEditor().setText(LocalDate.parse(oneUser.getDateDebut(),DateTimeFormatter.ofPattern("d/MM/uuuu")).toString());
            username.requestFocus();
        }
    }

}
