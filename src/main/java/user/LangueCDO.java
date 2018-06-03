/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author taleb
 */
public class LangueCDO {

    private final StringProperty langue = new SimpleStringProperty();

    public String getLangue() {
        return langue.get();
    }

    public void setLangue(String value) {
        langue.set(value);
    }

    public StringProperty langueProperty() {
        return langue;
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

    @Override
    public String toString() {
        return langue.getValue(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
