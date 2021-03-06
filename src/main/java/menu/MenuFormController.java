/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import app.ApplicationController;
import app.ApplicationFormAddController;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import rest.dto.component.ComponentContent;
import rest.dto.component.ComponentData;
import rest.dto.component.ComponentDataForm;
import rest.dto.component.ComponentDataIDListServerAnswer;
import rest.dto.component.ComponentForm;
import rest.dto.component.ComponentServerResponse;
import rest.dto.component.OneComponentServerResponse;
import tdo.component.ComponentDataTDO;
import tdo.component.ComponentTDO;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class MenuFormController extends GenericFXController implements Initializable {


    private static boolean mode = ADD_MODE;
    private static Long menu_id;

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
    @FXML
    private JFXTextField menuName;
    @FXML
    private JFXTextField rechercheParentField;
    @FXML
    private TableView<ComponentTDO> tableParent;
    @FXML
    private TableColumn<ComponentTDO, Boolean> colChecked;
    @FXML
    private TableColumn<ComponentTDO, String> colParrent;

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
            // TODO
            initTable();
            initValidation();
            if (mode == EDIT_MODE) {
                initData();
            } else {
                initAllParents(null);
            }
            colChecked.setEditable(true);
            tableParent.setEditable(true);

        } catch (UnirestException ex) {
            Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void doAddAttribut(ActionEvent event) {
        final ComponentDataTDO addTableValue = new ComponentDataTDO().withCode(attCode.getText()).withTitle(attTitle.getText()).withValue(attVal.getText());
        if (!tableAtributes.getItems().contains(addTableValue)) {
            tableAtributes.getItems().add(addTableValue);
        }

    }

    @FXML
    private void doDelete(ActionEvent event) {
        tableAtributes.getItems().remove(tableAtributes.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void doCommit(ActionEvent event) throws UnirestException {
        if (validate() && checkTable()) {
            if (mode == ADD_MODE) {
                try {
                    postMenuDetails();
                } catch (IOException ex) {
                    Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    updateMenuDetails();
                } catch (IOException ex) {
                    Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            // insert component data 
            // link componentdata to menu
            // link menu to all parent

        }
    }

    private ObservableList<ComponentTDO> allParents;

    @FXML
    private void doSearchParent(KeyEvent event) {
        tableParent.getItems().clear();
        if (rechercheParentField.getText().isEmpty()) {
            tableParent.getItems().addAll(allParents);
            return;
        }
        Predicate<? super ComponentTDO> SearchParentNamePredicate = parentComponent -> {
            return parentComponent.getComponentName().toLowerCase().contains(rechercheParentField.getText().toLowerCase());
        };
        allParents.stream().filter(SearchParentNamePredicate).forEach(parent -> {
            tableParent.getItems().add(parent);
        });
    }

    private void initTable() {
        colAttCode.setCellValueFactory(new PropertyValueFactory<>("attCode"));
        colAttTitle.setCellValueFactory(new PropertyValueFactory<>("attTitle"));
        colAttVal.setCellValueFactory(new PropertyValueFactory<>("attValue"));

        colChecked.setCellValueFactory(new PropertyValueFactory<>("selectedComponent"));
        colChecked.setCellFactory(tableCell -> new CheckBoxTableCell<>());

        colParrent.setCellValueFactory(new PropertyValueFactory<>("componentName"));

    }

    private void initValidation() {
        addRequiredElement(menuName);
    }

    private void initData() throws UnirestException, IOException {
        HttpResponse<String> asString = Unirest.get(MENU_SERVICE_URL+"/{menu_id}").routeParam("menu_id", ""+menu_id).asString();
        if (asString.getStatus() == 200) {

            OneComponentServerResponse oneServerReponse = new ObjectMapper().readValue(asString.getBody(), OneComponentServerResponse.class);
            ComponentContent menu = oneServerReponse.getContent();

            Predicate<? super ComponentData> menuNamePredicate = menuData -> {
                return menuData.getCmpAttrLabel().equals("menuName");
            };
            String menuNameAtt = menu.getCmpDatas().stream().filter(menuNamePredicate).findFirst().get().getCmpAttrValue();
            this.menuName.setText(menuNameAtt);

            tableAtributes.getItems().clear();
            Predicate<? super ComponentData> notMenuNamePredicate = menuData -> {
                return !menuData.getCmpAttrLabel().equals("menuName");
            };
            menu.getCmpDatas().stream().filter(notMenuNamePredicate).forEach(menuData -> {
                final ComponentDataTDO componentDataTDO = new ComponentDataTDO();
                componentDataTDO.setAttCode(menuData.getCmpAttrCode());
                componentDataTDO.setAttTitle(menuData.getCmpAttrLabel());
                componentDataTDO.setAttValue(menuData.getCmpAttrValue());
                tableAtributes.getItems().add(componentDataTDO);
            });
            initAllParents(menu);

        }
    }

    public void initAllParents(ComponentContent menu) throws UnirestException, IOException {
        // get all applications
        HttpResponse<String> asStringApps = Unirest.get(APPLICATION_SERVICE_URL).asString();
        List<ComponentContent> applications = new ArrayList();
        if (asStringApps.getStatus() == 200) {
            applications = new ObjectMapper().readValue(asStringApps.getBody(), ComponentServerResponse.class).getContent();

        } else {
            showErrorAlert("service d'application en panne : error code " + asStringApps.getStatus());
        }
        // get all menus
        HttpResponse<String> asStringMenus = Unirest.get(MENU_SERVICE_URL).asString();
        List<ComponentContent> menus = new ArrayList();
        if (asStringMenus.getStatus() == 200) {
            menus = new ObjectMapper().readValue(asStringMenus.getBody(), ComponentServerResponse.class).getContent();

        } else {
            showErrorAlert("service des menus en panne : error code " + asStringMenus.getStatus());
        }
        menus.remove(menu);
        
        // union between two sets
        List<ComponentContent> eligibleParents = new ArrayList();
        eligibleParents.addAll(menus);
        eligibleParents.addAll(applications);
        // add all to view
        tableParent.getItems().clear();
        eligibleParents.stream().forEach(parent -> {

            Boolean selected = false;
            // if current add is in menu parent set selected true else false
            if (menu != null) {
                if (menu.getCmpParents().contains(parent)) {
                    selected = true;
                } else {
                }
            }
            final ComponentTDO parentTDO = new ComponentTDO();
            parentTDO.setSelectedComponent(selected);

            parentTDO.setComponent(parent);

            String attLabel = "";
            switch (parent.getCmpType()) {
                case "APPLICATION":
                    attLabel = "appName";
                    break;
                case "MENU":
                    attLabel = "menuName";
                    break;
            }
            Predicate<? super ComponentData> compenentNamePredicate = new ComponentNamePredicate(attLabel);
            String parentName = parent.getCmpDatas().stream().filter(compenentNamePredicate).findFirst().get().getCmpAttrValue();
            parentTDO.setComponentName(parentName);

            parentTDO.setComponentID(parent.getId());

            tableParent.getItems().add(parentTDO);

        });
        allParents = FXCollections.observableArrayList();
        allParents.addAll(tableParent.getItems());
    }

    public static void setMode(boolean mode, Long menu_id) {
        MenuFormController.mode = mode;
        MenuFormController.menu_id = menu_id;
        System.out.println("with menu id = " + menu_id);
    }

    private boolean checkTable() {
        Predicate<? super ComponentTDO> isSelectedPredicate = parent -> {
            return parent.isSelectedComponent();
        };
        return tableParent.getItems().stream().filter(isSelectedPredicate).count() != 0;
    }

    private void updateMenuDetails() throws IOException, UnirestException {
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
        requestItem.setAttCode("menuName");
        requestItem.setAttTitle("menuName");
        requestItem.setAttVal(menuName.getText());
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
            List<Long> componentDetailIDs;
            final ObjectMapper objectMapper = new ObjectMapper();
            componentDetailIDs = objectMapper.readValue(idsAsString, ComponentDataIDListServerAnswer.class).getContent();
            ComponentForm componentForm = new ComponentForm();
            componentForm.setComponent_name("");
            componentForm.setId_datas(componentDetailIDs);
            componentForm.setId_fils(new ArrayList<>());
            final ArrayList<Long> parentsIDs = new ArrayList<>();
            Predicate<? super ComponentTDO> selectedParentPredicate = parent -> {
                return parent.isSelectedComponent();
            };
            tableParent.getItems().stream().filter(selectedParentPredicate).forEach(parent -> {
                parentsIDs.add(parent.getComponentID());
            });
            componentForm.setId_parents(parentsIDs);
            HttpResponse<String> asString1 = Unirest.put(MENU_SERVICE_URL)
                    .queryString("componentForm", componentForm)
                    .queryString("menu_id", menu_id)
                    .asString();
            System.out.println(asString1.getStatus());
            System.out.println(asString1.getBody());
            ApplicationController.doSycnh = true;
        }

    }

    private void postMenuDetails() throws IOException, UnirestException {
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
        requestItem.setAttCode("menuName");
        requestItem.setAttTitle("menuName");
        requestItem.setAttVal(menuName.getText());
        componentDataFormValues.add(requestItem);
        HttpResponse<String> asString = Unirest.post(COMPONENT_DATA_SERVICE_URL)
                .header("content-type", "application/json")
                .body( componentDataFormValues)
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
            final ArrayList<Long> parentsIDs = new ArrayList<>();
            Predicate<? super ComponentTDO> selectedParentPredicate = parent -> {
                return parent.isSelectedComponent();
            };
            tableParent.getItems().stream().filter(selectedParentPredicate).forEach(parent -> {
                parentsIDs.add(parent.getComponentID());
            });
            componentForm.setId_parents(parentsIDs);
            HttpResponse<String> asString1 = Unirest.post(MENU_SERVICE_URL)
                    .queryString("componentForm", componentForm)
                    .asString();
            System.out.println(asString1.getStatus());
            System.out.println(asString1.getBody());
            ApplicationController.doSycnh = true;
        }

    }

    private static class ComponentNamePredicate implements Predicate<ComponentData> {

        private final String attLabel;

        public ComponentNamePredicate(String attLabel) {
            this.attLabel = attLabel;
        }

        @Override
        public boolean test(ComponentData parentData) {
            return parentData.getCmpAttrLabel().equals(attLabel);
        }
    }

}
