/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author taleb
 */
public class AppDataAttTDO {

    private final StringProperty attCode = new SimpleStringProperty();

    public String getAttCode() {
        return attCode.get();
    }

    public void setAttCode(String value) {
        attCode.set(value);
    }

    public StringProperty attCodeProperty() {
        return attCode;
    }
    private final StringProperty attTitle = new SimpleStringProperty();

    public String getAttTitle() {
        return attTitle.get();
    }

    public void setAttTitle(String value) {
        attTitle.set(value);
    }

    public StringProperty attTitleProperty() {
        return attTitle;
    }
    private final StringProperty attValue = new SimpleStringProperty();

    public String getAttValue() {
        return attValue.get();
    }

    public void setAttValue(String value) {
        attValue.set(value);
    }

    public StringProperty attValueProperty() {
        return attValue;
    }

    public AppDataAttTDO() {
    }
    
    public AppDataAttTDO withCode(String code){
        this.setAttCode(code);
        return this;
    }
    
    public AppDataAttTDO withTitle(String title){
        this.setAttTitle(title);
        return this;
    }
    
    public AppDataAttTDO withValue(String value){
        this.setAttValue(value);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.attCode);
        hash = 17 * hash + Objects.hashCode(this.attTitle);
        hash = 17 * hash + Objects.hashCode(this.attValue);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AppDataAttTDO other = (AppDataAttTDO) obj;
        if (this.attCode.equals(other.attCode)) {
            return false;
        }
        if (this.attTitle.equals(other.attTitle)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "attCode=" + attCode.get() + ", attTitle=" + attTitle.get() + ", attValue=" + attValue.get() + '}';
    }
    
    
}
