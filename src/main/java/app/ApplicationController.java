/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import tdo.component.ComponentDataTDO;
import tdo.component.ComponentTDO;
import rest.dto.component.ComponentData;
import rest.dto.component.ComponentContent;
import rest.dto.component.ComponentServerResponse;
import application.data.ApplicationGlobalData;
import application.data.GenericFXController;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class ApplicationController extends GenericFXController implements Initializable {

    public static boolean doSycnh;

    @FXML
    private TableColumn<ComponentTDO, String> columnAppName;
    @FXML
    private JFXTextField searchField;

    @FXML
    private TableView<ComponentTDO> tableApplication;
    @FXML
    private TableView<ComponentDataTDO> tableApplicationDetail;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnCodeAttribut;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnTitreAttribut;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnValeur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getAllApplications();
            initTable();
        } catch (UnirestException | IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void doSearch(KeyEvent event) {

        tableApplication.getItems().clear();
        if (searchField.getText().isEmpty()) {
            populateTable();
        } else {
            Predicate<? super ComponentContent> prdct = appCont -> {
                Predicate<? super ComponentData> appNameData = appData -> {return appData.getCmpAttrLabel().equals("appName"); };
                return appCont.getCmpDatas().stream().filter(appNameData).findFirst().get().getCmpAttrValue().toLowerCase().contains(searchField.getText().toLowerCase());
            };
            populateTable(applications.stream().filter(prdct).collect(Collectors.toList()));
        }

    }

    private void initTable() {
        tableApplication.getItems().clear();

        populateTable();

        tableApplicationDetail.getItems().clear();
        tableApplication.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                tableApplicationDetail.getItems().clear();
                newV.getComponent().getCmpDatas().stream().forEach(appData -> {
                    final ComponentDataTDO appDataAttTDO = new ComponentDataTDO();
                    appDataAttTDO.setAttCode(appData.getCmpAttrCode());
                    appDataAttTDO.setAttTitle(appData.getCmpAttrLabel());
                    appDataAttTDO.setAttValue(appData.getCmpAttrValue());
                    tableApplicationDetail.getItems().add(appDataAttTDO);
                });
            }
        });

        columnAppName.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        columnCodeAttribut.setCellValueFactory(new PropertyValueFactory<>("attCode"));
        columnTitreAttribut.setCellValueFactory(new PropertyValueFactory<>("attTitle"));
        columnValeur.setCellValueFactory(new PropertyValueFactory<>("attValue"));

    }

    public void populateTable() {
        applications.stream().forEach(app -> {
            final ComponentTDO applicationTDO = new ComponentTDO();
            applicationTDO.setComponentID(app.getId());
            Predicate<? super ComponentData> prdct = appData -> {
                return appData.getCmpAttrLabel().equals("appName");
            };
            applicationTDO.setComponentName(app.getCmpDatas().stream().filter(prdct).findFirst().get().getCmpAttrValue());
            applicationTDO.setComponent(app);
            tableApplication.getItems().add(applicationTDO);
        });
    }

    public void populateTable(List<ComponentContent> applications) {
        applications.stream().forEach(app -> {
            final ComponentTDO applicationTDO = new ComponentTDO();
            applicationTDO.setComponentID(app.getId());
            Predicate<? super ComponentData> prdct = appData -> {
                return appData.getCmpAttrLabel().equals("appName");
            };
            applicationTDO.setComponentName(app.getCmpDatas().stream().filter(prdct).findFirst().get().getCmpAttrValue());
            applicationTDO.setComponent(app);
            tableApplication.getItems().add(applicationTDO);
        });
    }

    private List<ComponentContent> applications;

    private void getAllApplications() throws UnirestException, IOException {

        HttpResponse<String> asString = Unirest.get(APPLICATION_SERVICE_URL).asString();
        System.out.println(asString.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        ComponentServerResponse applicationServerResponse = objectMapper.readValue(asString.getBody(), ComponentServerResponse.class);
        applications = applicationServerResponse.getContent();

    }

    @FXML
    private void showData(ActionEvent event) {
    }

    @FXML
    private void showAdd(ActionEvent event) {
        ApplicationFormAddController.setMode(ApplicationFormAddController.ADD_MODE, Long.valueOf(-1));
        showModalForm(event, "/fxml/application/ApplicationForm.fxml");
        if (doSycnh) {
            doSycnh = false;
            try {
                getAllApplications();
            } catch (UnirestException | IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showEdit(ActionEvent event) {
        ApplicationFormAddController.setMode(ApplicationFormAddController.EDIT_MODE, tableApplication.getSelectionModel().getSelectedItem().getComponentID());
        showModalForm(event, "/fxml/application/ApplicationForm.fxml");
        if (doSycnh) {
            doSycnh = false;
            try {
                getAllApplications();
            } catch (UnirestException | IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void doDelete(ActionEvent event) throws UnirestException {
        final ComponentTDO selectedApplication = tableApplication.getSelectionModel().getSelectedItem();
        boolean showConfirmAlert = showConfirmAlert(event, "voulez vous vraiment Suprimer l application sellectioner \n " + selectedApplication.getComponentName());
        if (showConfirmAlert) {
            HttpResponse<String> asString = Unirest.delete(APPLICATION_SERVICE_URL)
                    .queryString("app_id", selectedApplication.getComponentID())
                    .asString();
            System.out.println(asString);
            if (asString.getStatus() == 200) {
                tableApplication.getItems().remove(selectedApplication);
                                Predicate<? super ComponentContent> idRemovePredicate = component -> {
                    return component.getId() == selectedApplication.getComponentID();
                };
                applications.removeIf(idRemovePredicate);
            }
        }

    }

}
