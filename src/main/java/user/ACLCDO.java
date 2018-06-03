/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author taleb
 */
public class ACLCDO {

    private final StringProperty aclTitle = new SimpleStringProperty();

    public String getAclTitle() {
        return aclTitle.get();
    }

    public void setAclTitle(String value) {
        aclTitle.set(value);
    }

    public StringProperty aclTitleProperty() {
        return aclTitle;
    }
    private final LongProperty id = new SimpleLongProperty();

    public long getId() {
        return id.get();
    }

    public void setId(long value) {
        id.set(value);
    }

    public LongProperty idProperty() {
        return id;
    }
    private final ListProperty<String> roles = new SimpleListProperty<>();

    public ObservableList getRoles() {
        return roles.get();
    }

    public void setRoles(ObservableList value) {
        roles.set(value);
    }

    public ListProperty rolesProperty() {
        return roles;
    }

    @Override
    public String toString() {
        return aclTitle.getValue(); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
