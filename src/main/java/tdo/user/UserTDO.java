/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdo.user;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import user.UserDTO;

/**
 *
 * @author taleb
 */
public class UserTDO {

    private final StringProperty username = new SimpleStringProperty();

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String value) {
        username.set(value);
    }

    public StringProperty usernameProperty() {
        return username;
    }
    private final LongProperty userID = new SimpleLongProperty();

    public long getUserID() {
        return userID.get();
    }

    public void setUserID(long value) {
        userID.set(value);
    }

    public LongProperty userIDProperty() {
        return userID;
    }
    private final StringProperty acl = new SimpleStringProperty();

    public String getAcl() {
        return acl.get();
    }

    public void setAcl(String value) {
        acl.set(value);
    }

    public StringProperty aclProperty() {
        return acl;
    }
    private final ObjectProperty<UserDTO> userDTO = new SimpleObjectProperty<>();

    public UserDTO getUserDTO() {
        return userDTO.get();
    }

    public void setUserDTO(UserDTO value) {
        userDTO.set(value);
    }

    public ObjectProperty userContentProperty() {
        return userDTO;
    }
    
    
    
}
