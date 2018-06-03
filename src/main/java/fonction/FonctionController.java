/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonction;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import menu.MenuController;
import rest.dto.component.ComponentContent;
import rest.dto.component.ComponentData;
import rest.dto.component.ComponentServerResponse;
import tdo.component.ComponentDataTDO;
import tdo.component.ComponentTDO;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class FonctionController extends GenericFXController implements Initializable {

    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<ComponentTDO> tableFonction;
    @FXML
    private TableColumn<ComponentTDO, String> columnFonctionName;
    @FXML
    private TableView<ComponentTDO> tableParent;
    @FXML
    private TableColumn<ComponentTDO, String> columnParentName;
    @FXML
    private TableView<ComponentDataTDO> tableFonctionDetail;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnCodeAttribut;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnTitreAttribut;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnValeur;


    public static boolean doSynch = false;

    private ComponentServerResponse componentServerResponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO

            getAllFonctions();
            initTables();
        } catch (UnirestException | IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void doSearch(KeyEvent event) {
        componentServerResponse.getContent().stream().forEach(fonction -> {
            Predicate<? super ComponentData> fonctionTitlePredicate = currentFonction -> {
                return currentFonction.getCmpAttrCode().equals("functionName") && currentFonction.getCmpAttrValue().toLowerCase().contains(searchField.getText().toLowerCase());
            };
            ComponentData fonctionName = fonction.getCmpDatas().stream().filter(fonctionTitlePredicate).findFirst().get();
            final ComponentTDO functionTDO = new ComponentTDO();
            functionTDO.setComponentName(fonctionName.getCmpAttrValue());
            functionTDO.setComponent(fonction);
            functionTDO.setComponentID(fonction.getId());
            tableFonction.getItems().add(functionTDO);
        });
    }

    @FXML
    private void showData(ActionEvent event) {
    }

    @FXML
    private void showAdd(ActionEvent event) {
        FonctionFormController.setMode(FonctionFormController.ADD_MODE, Long.valueOf(-1));
        showModalForm(event, EDIT_FORM_FXML);
        if (doSynch) {
            doSynch = false;
            try {
                getAllFonctions();
            } catch (UnirestException | IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static final String EDIT_FORM_FXML = "/fxml/fonction/FonctionForm.fxml";

    @FXML
    private void showEdit(ActionEvent event) {
        FonctionFormController.setMode(FonctionFormController.EDIT_MODE, tableFonction.getSelectionModel().getSelectedItem().getComponentID());
        showModalForm(event, EDIT_FORM_FXML);

        if (doSynch) {
            doSynch = false;
            try {
                getAllFonctions();
            } catch (UnirestException | IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void doDelete(ActionEvent event) throws UnirestException {
        final ComponentTDO toDelete = tableFonction.getSelectionModel().getSelectedItem();
        boolean confirmDelete = showConfirmAlert(event, "voulez vous vraiment supprimer :" + toDelete.getComponentName());
        if (confirmDelete) {
            HttpResponse<String> asString = Unirest.delete(FONCTION_SERVICE_URL).queryString("fonction_id", toDelete.getComponentID()).asString();
            if (asString.getStatus() == 200) {
                tableFonction.getItems().remove(toDelete);
                tableFonction.getSelectionModel().selectFirst();
                                Predicate<? super ComponentContent> idRemovePredicate = component -> {
                    return component.getId() == toDelete.getComponentID();
                };
                componentServerResponse.getContent().removeIf(idRemovePredicate);
            }
        }
    }

    private void getAllFonctions() throws UnirestException, IOException {
        HttpResponse<String> asString = Unirest.get(FONCTION_SERVICE_URL).asString();
        if (asString.getStatus() == 200) {

            componentServerResponse = new ObjectMapper().readValue(asString.getBody(), ComponentServerResponse.class);

        }
    }

    private void initTables() {
        tableFonction.getItems().clear();
        tableFonctionDetail.getItems().clear();
        tableParent.getItems().clear();
        componentServerResponse.getContent().stream().forEach(fonction -> {
            Predicate<? super ComponentData> fonctionTitlePredicate = currentFonction -> {
                return currentFonction.getCmpAttrCode().equals("fonctionName");
            };
            ComponentData fonctionName = fonction.getCmpDatas().stream().filter(fonctionTitlePredicate).findFirst().get();
            final ComponentTDO fonctionTDO = new ComponentTDO();
            fonctionTDO.setComponentName(fonctionName.getCmpAttrValue());
            fonctionTDO.setComponent(fonction);
            fonctionTDO.setComponentID(fonction.getId());
            tableFonction.getItems().add(fonctionTDO);
        });

        tableFonction.getSelectionModel().selectedItemProperty().addListener((obs, oldFonction, newFonction) -> {
            if (newFonction != null) {
                tableParent.getItems().clear();
                tableFonctionDetail.getItems().clear();
                List<ComponentContent> cmpParents = newFonction.getComponent().getCmpParents();
                cmpParents.stream().forEach(parent -> {
                    String filterValue = "";
                    switch (parent.getCmpType()) {
                        case "APPLICATION":
                            filterValue = "appName";
                            break;
                        case "MENU":
                            filterValue = "menuName";
                            break;
                        case "FONCTION":
                            filterValue = "fonctionName";
                            break;
                    }
                    Predicate<? super ComponentData> componentName = new ComponentNamePredicate(filterValue);
                    String parentName = parent.getCmpDatas().stream().filter(componentName).findFirst().get().getCmpAttrValue();
                    final ComponentTDO parenttDO = new ComponentTDO();
                    parenttDO.setComponentName(parentName);
                    parenttDO.setComponentID(parent.getId());
                    parenttDO.setComponent(parent);
                    tableParent.getItems().add(parenttDO);
                });

                Predicate<? super ComponentData> notFonctionNameAtt = fonctionData -> {
                    return !fonctionData.getCmpAttrLabel().equals("fonctionName");
                };
                newFonction.getComponent().getCmpDatas().stream().filter(notFonctionNameAtt).forEach(fonctionAtt -> {
                    final ComponentDataTDO componentDataTDO = new ComponentDataTDO();
                    componentDataTDO.setAttCode(fonctionAtt.getCmpAttrCode());
                    componentDataTDO.setAttTitle(fonctionAtt.getCmpAttrLabel());
                    componentDataTDO.setAttValue(fonctionAtt.getCmpAttrValue());
                    tableFonctionDetail.getItems().add(componentDataTDO);
                });
            }
        });

        columnFonctionName.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        columnParentName.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        columnCodeAttribut.setCellValueFactory(new PropertyValueFactory<>("attCode"));
        columnTitreAttribut.setCellValueFactory(new PropertyValueFactory<>("attTitle"));
        columnValeur.setCellValueFactory(new PropertyValueFactory<>("attValue"));

    }

    private static class ComponentNamePredicate implements Predicate<ComponentData> {

        private final String filterValue;

        public ComponentNamePredicate(String filterValue) {
            this.filterValue = filterValue;
        }

        @Override
        public boolean test(ComponentData parentData) {
            return parentData.getCmpAttrLabel().equals(filterValue);
        }
    }
}
