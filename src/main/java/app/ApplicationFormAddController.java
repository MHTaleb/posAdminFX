/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import tdo.component.ComponentDataTDO;
import rest.dto.component.ComponentForm;
import rest.dto.component.ComponentDataForm;
import rest.dto.component.OneComponentServerResponse;
import rest.dto.component.ComponentData;
import rest.dto.component.ComponentContent;
import rest.dto.component.ComponentDataIDListServerAnswer;
import application.data.ApplicationGlobalData;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class ApplicationFormAddController extends GenericFXController implements Initializable {



    private static boolean mode = ADD_MODE;
    private static Long app_id;

    @FXML
    private JFXTextField appName;
    @FXML
    private JFXTextField attCode;
    @FXML
    private JFXTextField attTitle;
    @FXML
    private JFXTextField attVal;
    @FXML
    private TableView<ComponentDataTDO> tableAtributes;
    @FXML
    private TableColumn<ComponentDataTDO, String> colAttCode;
    @FXML
    private TableColumn<ComponentDataTDO, String> colAttTitle;
    @FXML
    private TableColumn<ComponentDataTDO, String> colAttVal;
    private ComponentContent applications;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initTable();
            initValidation();
            if (mode == EDIT_MODE) {
                initData();
            }

        } catch (UnirestException ex) {
            Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void doDelete(ActionEvent event) {
        if (!tableAtributes.getItems().isEmpty()) {
            tableAtributes.getItems().remove(tableAtributes.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void doCommit(ActionEvent event) throws UnirestException {

        if (validate()) {
            if (mode == ADD_MODE) {
                try {
                    postApplicationDetails();
                } catch (IOException ex) {
                    Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    updateApplicationDetails();
                } catch (IOException ex) {
                    Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            ApplicationController.doSycnh = true;
        }

    }

    public void postApplicationDetails() throws UnirestException, IOException {
        final ObservableList<ComponentDataTDO> items = tableAtributes.getItems();
        final List<ComponentDataForm> componentDataFormValues = new ArrayList();
        items.stream().forEach(item -> {
            final ComponentDataForm requestItem = new ComponentDataForm();
            requestItem.setAttCode(item.getAttCode());
            requestItem.setAttTitle(item.getAttTitle());
            requestItem.setAttVal(item.getAttValue());

            componentDataFormValues.add(requestItem);
        });
        final ComponentDataForm requestItem = new ComponentDataForm();
        requestItem.setAttCode("appName");
        requestItem.setAttTitle("appName");
        requestItem.setAttVal(appName.getText());
        componentDataFormValues.add(requestItem);
        HttpResponse<String> asString = Unirest.post(COMPONENT_DATA_SERVICE_URL)
                .header("content-type", "application/json")
                .body(componentDataFormValues)
                .asString();
        final int status = asString.getStatus();
        System.out.println(status);
        final String idsAsString = asString.getBody();
        System.out.println(idsAsString);
        if (status == 200) {
            List<Long> applicationDetailIDs;
            final ObjectMapper objectMapper = new ObjectMapper();
            applicationDetailIDs = objectMapper.readValue(idsAsString, ComponentDataIDListServerAnswer.class).getContent();
            ComponentForm componentForm = new ComponentForm();
            componentForm.setComponent_name("");
            componentForm.setId_datas(applicationDetailIDs);
            componentForm.setId_fils(new ArrayList<>());
            componentForm.setId_parents(new ArrayList<>());
            HttpResponse<String> asString1 = Unirest.post(APPLICATION_SERVICE_URL)
                    .queryString("componentForm", componentForm)
                    .asString();
            System.out.println(asString1.getStatus());
            System.out.println(asString1.getBody());
        }

    }

    public static void setMode(boolean mode, Long app_ID) {
        ApplicationFormAddController.mode = mode;
        ApplicationFormAddController.app_id = app_ID;
        System.out.println("with app id = "+app_ID);
    }

    private void initData() throws UnirestException {
        try {
            HttpResponse<String> asString = Unirest.get( APPLICATION_SERVICE_URL +"/{app_id}")
                    .routeParam("app_id", ""+app_id).asString();
            System.out.println(asString.getStatus());
            System.out.println(asString.getBody());
            OneComponentServerResponse applicationServerResponse = new ObjectMapper().readValue(asString.getBody(), OneComponentServerResponse.class);
            applications = applicationServerResponse.getContent();
            Predicate<? super ComponentData> appNamePredicate = appData -> {return appData.getCmpAttrLabel().equals("appName");};
            
            appName.setText(applications.getCmpDatas().stream().filter(appNamePredicate).findFirst().get().getCmpAttrValue());
            Predicate<? super ComponentData> notAppNamePredicate = appData -> {return !appData.getCmpAttrLabel().equals("appName");};
            tableAtributes.getItems().clear();
            applications.getCmpDatas().stream().filter(notAppNamePredicate).forEach(appData -> {
                final ComponentDataTDO appDataAttTDO = new ComponentDataTDO();
                appDataAttTDO.setAttCode(appData.getCmpAttrCode());
                appDataAttTDO.setAttTitle(appData.getCmpAttrLabel());
                appDataAttTDO.setAttValue(appData.getCmpAttrValue());
                tableAtributes.getItems().add(appDataAttTDO);
            });
            
        } catch (IOException ex) {
            Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private List<ComponentDataTDO> globalList = new ArrayList();

    private void initTable() {
        tableAtributes.getItems().clear();
        tableAtributes.getItems().addAll(globalList);

        colAttCode.setCellValueFactory(new PropertyValueFactory<>("attCode"));
        colAttTitle.setCellValueFactory(new PropertyValueFactory<>("attTitle"));
        colAttVal.setCellValueFactory(new PropertyValueFactory<>("attValue"));

    }

    private void initValidation() {
        addRequiredElement(appName);
//        addRequiredElement(attCode);
//        addRequiredElement(attTitle);
//        addRequiredElement(attVal);
    }

    @FXML
    private void doAddAttribut(ActionEvent event) {
        final ComponentDataTDO item = new ComponentDataTDO().withCode(attCode.getText()).withTitle(attTitle.getText()).withValue(attVal.getText());
        Predicate<? super ComponentDataTDO> prdct = ada -> {
            return ada.getAttCode().equals(item.getAttCode()) || ada.getAttTitle().equals(item.getAttTitle());
        };
        if (tableAtributes.getItems().stream().filter(prdct).toArray().length == 0) {
            tableAtributes.getItems().add(item);
        }
    }

    private void updateApplicationDetails() throws UnirestException, IOException {
        final ObservableList<ComponentDataTDO> items = tableAtributes.getItems();
        final List<ComponentDataForm> componentDataFormValues = new ArrayList();
        items.stream().forEach(item -> {
            final ComponentDataForm requestItem = new ComponentDataForm();
            requestItem.setAttCode(item.getAttCode());
            requestItem.setAttTitle(item.getAttTitle());
            requestItem.setAttVal(item.getAttValue());

            componentDataFormValues.add(requestItem);
        });
        final ComponentDataForm requestItem = new ComponentDataForm();
        requestItem.setAttCode("appName");
        requestItem.setAttTitle("appName");
        requestItem.setAttVal(appName.getText());
        componentDataFormValues.add(requestItem);
        HttpResponse<String> asString = Unirest.post(COMPONENT_DATA_SERVICE_URL)
                .header("content-type", "application/json")
                .body(componentDataFormValues)
                .asString();
        final int status = asString.getStatus();
        System.out.println(status);
        final String idsAsString = asString.getBody();
        System.out.println(idsAsString);
        if (status == 200) {
            List<Long> applicationDetailIDs;
            final ObjectMapper objectMapper = new ObjectMapper();
            applicationDetailIDs = objectMapper.readValue(idsAsString, ComponentDataIDListServerAnswer.class).getContent();
            ComponentForm componentForm = new ComponentForm();
            componentForm.setComponent_name("");
            componentForm.setId_datas(applicationDetailIDs);
            componentForm.setId_fils(new ArrayList<>());
            componentForm.setId_parents(new ArrayList<>());
            HttpResponse<String> asString1 = Unirest.put(APPLICATION_SERVICE_URL)
                    .queryString("componentForm", componentForm)
                    .queryString("id", app_id)
                    .asString();
            System.out.println(asString1.getStatus());
            System.out.println(asString1.getBody());
        }

    }

}
