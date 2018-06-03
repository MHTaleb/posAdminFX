/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdo.component;

import rest.dto.component.ComponentContent;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author taleb
 */
public class ComponentTDO {

    private final StringProperty componentName = new SimpleStringProperty();

    public String getComponentName() {
        return componentName.get();
    }

    public void setComponentName(String value) {
        componentName.set(value);
    }

    public StringProperty componentNameProperty() {
        return componentName;
    }
    private final LongProperty componentID = new SimpleLongProperty();

    public long getComponentID() {
        return componentID.get();
    }

    public void setComponentID(long value) {
        componentID.set(value);
    }

    public LongProperty componentIDProperty() {
        return componentID;
    }

    public ComponentTDO withID(Long id) {
        this.setComponentID(id);
        return this;
    }

    public ComponentTDO withComponentName(String appName) {
        this.setComponentName(appName);
        return this;
    }
    private final ObjectProperty<ComponentContent> component = new SimpleObjectProperty<>();

    public ComponentContent getComponent() {
        return component.get();
    }

    public void setComponent(ComponentContent value) {
        component.set(value);
    }

    public ObjectProperty componentProperty() {
        return component;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.componentName);
        hash = 67 * hash + Objects.hashCode(this.componentID);
        hash = 67 * hash + Objects.hashCode(this.component);
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
        final ComponentTDO other = (ComponentTDO) obj;
        if (!Objects.equals(this.componentName, other.componentName)) {
            return false;
        }
        if (!Objects.equals(this.componentID, other.componentID)) {
            return false;
        }
        if (!Objects.equals(this.component, other.component)) {
            return false;
        }
        return true;
    }
    private final BooleanProperty selectedComponent = new SimpleBooleanProperty();

    public boolean isSelectedComponent() {
        return selectedComponent.get();
    }

    public void setSelectedComponent(boolean value) {
        selectedComponent.set(value);
    }

    public BooleanProperty selectedComponentProperty() {
        return selectedComponent;
    }

}
