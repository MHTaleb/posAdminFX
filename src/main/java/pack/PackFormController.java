/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import rest.dto.pack.OnePackServerResponse;
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
import java.util.stream.Collectors;
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
import rest.dto.component.ComponentServerResponse;
import rest.dto.pack.PackForm;
import tdo.component.ComponentTDO;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class PackFormController extends GenericFXController implements Initializable {

    @FXML
    private JFXTextField packName;
    @FXML
    private JFXTextField recherchePack;
    @FXML
    private TableView<ComponentTDO> tableApplications;
    @FXML
    private TableColumn<ComponentTDO, Boolean> colChecked;
    @FXML
    private TableColumn<ComponentTDO, String> colApplication;

    private static boolean mode = ADD_MODE;
    private static Long pack_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getAllApps();
            initTables();
            if (mode == EDIT_MODE) {
                initData();
            }
        } catch (UnirestException | IOException ex) {
            Logger.getLogger(PackFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void doSearchParent(KeyEvent event) {
        initTables();
        if (!recherchePack.getText().isEmpty()) {
            Predicate<? super ComponentTDO> appNameSearchPredicate = app -> {
                return !app.getComponentName().contains(recherchePack.getText().toLowerCase());
            };
            tableApplications.getItems().removeAll(tableApplications.getItems().stream().filter(appNameSearchPredicate).collect(Collectors.toList()));
        }
    }

    public static void setMode(boolean mode, Long pack_id) {
        PackFormController.mode = mode;
        PackFormController.pack_id = pack_id;
        System.out.println("with menu id = " + pack_id);
    }

    @FXML
    private void doCommit(ActionEvent event) throws UnirestException {

        if (validate() && tableIsValid()) {
            if (mode == ADD_MODE) {
                postPack();

            } else {
                putPack();
            }

        }
    }

    public void postPack() throws UnirestException {
        PackForm packForm = new PackForm();

        Predicate<? super ComponentTDO> selectedAppsPredicate = app -> {
            return app.isSelectedComponent();
        };
        List<ComponentTDO> collect = tableApplications.getItems().stream().filter(selectedAppsPredicate).collect(Collectors.toList());
        List<Long> components = new ArrayList();
        collect.stream().forEach(app -> {
            components.add(app.getComponent().getId());
        });

        packForm.setPackApplications(components);

        packForm.setPackName(packName.getText());
        System.out.println(packForm);
        HttpResponse<String> asString = Unirest.post(PACK_SERVER_URL)
                .header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_APPLICATION_JSON)
                .body(packForm)
                .asString();
        if (asString.getStatus() == 200) {
            System.out.println(asString.getBody());
        }
    }

    private ObservableList<ComponentContent> observableApplications;

    private void getAllApps() throws UnirestException, IOException {
        List<ComponentContent> applications;
        HttpResponse<String> asString = Unirest.get(APPLICATION_SERVICE_URL).asString();
        if (asString.getStatus() == 200) {
            ComponentServerResponse applicationsResponse = new ObjectMapper().readValue(asString.getBody(), ComponentServerResponse.class);
            applications = applicationsResponse.getContent();
            observableApplications = FXCollections.observableArrayList();
            applications.stream().forEach(observableApplications::add);
        }
    }

    private void initTables() {
        tableApplications.getItems().clear();
        observableApplications.stream().forEach(
                app -> {
                    final ComponentTDO componentTDO = new ComponentTDO();
                    componentTDO.setComponent(app);
                    componentTDO.setComponentID(app.getId());
                    Predicate<? super ComponentData> appNameProdicate = appData -> {
                        return appData.getCmpAttrLabel().equals("appName");
                    };
                    componentTDO.setComponentName(app.getCmpDatas().stream().filter(appNameProdicate).findFirst().get().getCmpAttrValue());
                    tableApplications.getItems().add(componentTDO);
                }
        );

        colChecked.setCellValueFactory(new PropertyValueFactory<>("selectedComponent"));
        colChecked.setCellFactory(tableCell -> new CheckBoxTableCell<>());
        tableApplications.setEditable(true);
        colApplication.setCellValueFactory(new PropertyValueFactory<>("componentName"));
    }

    private boolean tableIsValid() {
        Predicate<ComponentTDO> selectedAppsPredicate = app -> {
            return app.isSelectedComponent();
        };
        final boolean tableStateEmpty = !tableApplications.getItems().filtered(selectedAppsPredicate).isEmpty();
        if (!tableStateEmpty) {
            showErrorAlert("Veuillez selectioner une application au moins dans ce pack ");
        }
        return tableStateEmpty;
    }

    private void initData() throws UnirestException, IOException {
        try {
            HttpResponse<String> asString = Unirest.get(PACK_SERVER_URL + "/{pack_id}")
                    .routeParam("pack_id", "" + pack_id)
                    .asString();
            System.out.println(asString.getBody());
            if (asString.getStatus() == 200) {
                OnePackServerResponse pack = new ObjectMapper().readValue(asString.getBody(), OnePackServerResponse.class);
                pack.getPackContent().getMngComposants().stream().forEach(app -> {
                    Predicate<ComponentTDO> appIdPredicate = appTDO -> {
                        return appTDO.getComponentID() == app.getId();
                    };
                    tableApplications.getItems().filtered(appIdPredicate).get(0).setSelectedComponent(true);
                });
                packName.setText(pack.getPackContent().getPackName());
            }
        } catch (UnirestException ex) {
            Logger.getLogger(PackFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void putPack() throws UnirestException {
        PackForm packForm = new PackForm();

        packForm.setId(pack_id);

        Predicate<? super ComponentTDO> selectedAppsPredicate = app -> {
            return app.isSelectedComponent();
        };
        List<ComponentTDO> collect = tableApplications.getItems().stream().filter(selectedAppsPredicate).collect(Collectors.toList());
        List<Long> components = new ArrayList();
        collect.stream().forEach(app -> {
            components.add(app.getComponent().getId());
        });

        packForm.setPackApplications(components);

        packForm.setPackName(packName.getText());

        HttpResponse<String> asString = Unirest.put(PACK_SERVER_URL)
                .header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_APPLICATION_JSON)
                .body(packForm)
                .asString();
        if (asString.getStatus() == 200) {
            System.out.println(asString.getBody());
        }
    }

}
