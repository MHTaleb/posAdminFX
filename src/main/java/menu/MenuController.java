/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import application.data.ApplicationGlobalData;
import application.data.GenericFXController;
import com.fasterxml.jackson.databind.ObjectMapper;
import tdo.component.ComponentTDO;
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
import rest.dto.component.ComponentContent;
import rest.dto.component.ComponentData;
import rest.dto.component.ComponentServerResponse;
import tdo.component.ComponentDataTDO;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class MenuController extends GenericFXController implements Initializable {

    @FXML
    private JFXTextField searchField;
    @FXML
    private TableColumn<ComponentTDO, String> columnAppName;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnCodeAttribut;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnTitreAttribut;
    @FXML
    private TableColumn<ComponentDataTDO, String> columnValeur;
    @FXML
    private TableView<MenuTDO> tableMenu;
    @FXML
    private TableView<ComponentTDO> tableApplicationMenu;
    @FXML
    private TableView<ComponentDataTDO> tableMenuDetailDetail;
    @FXML
    private TableColumn<MenuTDO, String> columnMenuName;

    private final String SERVICE_URL = ApplicationGlobalData.SERVER_URL + "/menus";

    public static boolean doSynch = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getAllMenus();
            initTables();
        } catch (UnirestException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void doSearch(KeyEvent event) {
        componentServerResponse.getContent().stream().forEach(menu -> {
            Predicate<? super ComponentData> menuTitlePredicate = currentMenu -> {
                return currentMenu.getCmpAttrCode().equals("menuName") && currentMenu.getCmpAttrValue().toLowerCase().contains(searchField.getText().toLowerCase());
            };
            ComponentData menuName = menu.getCmpDatas().stream().filter(menuTitlePredicate).findFirst().get();
            final MenuTDO menuTDO = new MenuTDO();
            menuTDO.setMenuName(menuName.getCmpAttrValue());
            menuTDO.setMenu(menu);
            tableMenu.getItems().add(menuTDO);
        });
    }

    @FXML
    private void showData(ActionEvent event) {
    }

    @FXML
    private void showAdd(ActionEvent event) {
        MenuFormController.setMode(MenuFormController.ADD_MODE, Long.valueOf(-1));
        showModalForm(event, "/fxml/menu/MenuForm.fxml");
        if (doSynch) {
            doSynch = false;
            try {
                getAllMenus();
            } catch (UnirestException | IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showEdit(ActionEvent event) {
        MenuFormController.setMode(MenuFormController.EDIT_MODE, tableMenu.getSelectionModel().getSelectedItem().getMenu().getId());
        showModalForm(event, "/fxml/menu/MenuForm.fxml");

        if (doSynch) {
            doSynch = false;
            try {
                 getAllMenus();
            } catch (UnirestException | IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void doDelete(ActionEvent event) throws UnirestException {
        final MenuTDO toDelete = tableMenu.getSelectionModel().getSelectedItem();
        boolean confirmDelete = showConfirmAlert(event, "voulez vous vraiment supprimer :" + toDelete.getMenuName());
        if (confirmDelete) {
            HttpResponse<String> asString = Unirest.delete(SERVICE_URL).queryString("menu_id", toDelete.getMenu().getId()).asString();
            if (asString.getStatus() == 200) {
                tableMenu.getItems().remove(toDelete);
                tableMenu.getSelectionModel().selectFirst();
                Predicate<? super ComponentContent> idRemovePredicate = component -> {
                    return component.getId() == toDelete.getMenu().getId();
                };
                componentServerResponse.getContent().removeIf(idRemovePredicate);
                
            }
        }
    }

    private void initTables() {
        tableMenu.getItems().clear();
        tableMenuDetailDetail.getItems().clear();
        tableApplicationMenu.getItems().clear();
        componentServerResponse.getContent().stream().forEach(menu -> {
            Predicate<? super ComponentData> menuTitlePredicate = currentMenu -> {
                return currentMenu.getCmpAttrCode().equals("menuName");
            };
            ComponentData menuName = menu.getCmpDatas().stream().filter(menuTitlePredicate).findFirst().get();
            final MenuTDO menuTDO = new MenuTDO();
            menuTDO.setMenuName(menuName.getCmpAttrValue());
            menuTDO.setMenu(menu);
            tableMenu.getItems().add(menuTDO);
        });

        tableMenu.getSelectionModel().selectedItemProperty().addListener((obs, oldMenu, newMenu) -> {
            if (newMenu != null) {
                tableApplicationMenu.getItems().clear();
                tableMenuDetailDetail.getItems().clear();
                List<ComponentContent> cmpParents = newMenu.getMenu().getCmpParents();
                cmpParents.stream().forEach(parent -> {
                    String filterValue = "";
                    switch (parent.getCmpType()) {
                        case "APPLICATION":
                            filterValue = "appName";
                            break;
                        case "MENU":
                            filterValue = "menuName";
                            break;
                    }
                    Predicate<? super ComponentData> componentName = new ComponentNamePredicate(filterValue);
                    String parentName = parent.getCmpDatas().stream().filter(componentName).findFirst().get().getCmpAttrValue();
                    final ComponentTDO parenttDO = new ComponentTDO();
                    parenttDO.setComponentName(parentName);
                    parenttDO.setComponentID(parent.getId());
                    parenttDO.setComponent(parent);
                    tableApplicationMenu.getItems().add(parenttDO);
                });

                Predicate<? super ComponentData> notMenuNameAtt = menuData -> {
                    return !menuData.getCmpAttrLabel().equals("menuName");
                };
                newMenu.getMenu().getCmpDatas().stream().filter(notMenuNameAtt).forEach(menuAtt -> {
                    final ComponentDataTDO componentDataTDO = new ComponentDataTDO();
                    componentDataTDO.setAttCode(menuAtt.getCmpAttrCode());
                    componentDataTDO.setAttTitle(menuAtt.getCmpAttrLabel());
                    componentDataTDO.setAttValue(menuAtt.getCmpAttrValue());
                    tableMenuDetailDetail.getItems().add(componentDataTDO);
                });
            }
        });

        columnMenuName.setCellValueFactory(new PropertyValueFactory<>("menuName"));
        columnAppName.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        columnCodeAttribut.setCellValueFactory(new PropertyValueFactory<>("attCode"));
        columnTitreAttribut.setCellValueFactory(new PropertyValueFactory<>("attTitle"));
        columnValeur.setCellValueFactory(new PropertyValueFactory<>("attValue"));

    }
    private ComponentServerResponse componentServerResponse;

    private void getAllMenus() throws UnirestException, IOException {
        HttpResponse<String> asString = Unirest.get(SERVICE_URL).asString();
        if (asString.getStatus() == 200) {

            componentServerResponse = new ObjectMapper().readValue(asString.getBody(), ComponentServerResponse.class);

        }
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
