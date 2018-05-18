/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.state;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author taleb
 */
public class RoleDTO {

    private final StringProperty role = new SimpleStringProperty();

    public String getRole() {
        return role.get();
    }

    public void setRole(String value) {
        role.set(value);
    }

    public StringProperty roleProperty() {
        return role;
    }
    
    
    
}
