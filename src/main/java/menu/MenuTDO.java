/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import rest.dto.component.ComponentContent;

/**
 *
 * @author taleb
 */
public class MenuTDO {

    private final StringProperty menuName = new SimpleStringProperty();
    private final ObjectProperty<ComponentContent> menu = new SimpleObjectProperty<>();

    public ComponentContent getMenu() {
        return menu.get();
    }

    public void setMenu(ComponentContent value) {
        menu.set(value);
    }

    public ObjectProperty menuProperty() {
        return menu;
    }

    
    
    public String getMenuName() {
        return menuName.get();
    }

    public void setMenuName(String value) {
        menuName.set(value);
    }

    public StringProperty menuNameProperty() {
        return menuName;
    }
    
       
    
    
}
