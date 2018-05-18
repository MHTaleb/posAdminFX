/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Objects;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author taleb
 */
public class ApplicationTDO {

    private final StringProperty appName = new SimpleStringProperty();

    public String getAppName() {
        return appName.get();
    }

    public void setAppName(String value) {
        appName.set(value);
    }

    public StringProperty appNameProperty() {
        return appName;
    }
    private final LongProperty appID = new SimpleLongProperty();

    public long getAppID() {
        return appID.get();
    }

    public void setAppID(long value) {
        appID.set(value);
    }

    public LongProperty appIDProperty() {
        return appID;
    }

    public ApplicationTDO withID(Long id) {
        this.setAppID(id);
        return this;
    }

    public ApplicationTDO withAppName(String appName) {
        this.setAppName(appName);
        return this;
    }
    private final ObjectProperty<ApplicationServerResponse> application = new SimpleObjectProperty<>();

    public ApplicationServerResponse getApplication() {
        return application.get();
    }

    public void setApplication(ApplicationServerResponse value) {
        application.set(value);
    }

    public ObjectProperty applicationProperty() {
        return application;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.appName);
        hash = 67 * hash + Objects.hashCode(this.appID);
        hash = 67 * hash + Objects.hashCode(this.application);
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
        final ApplicationTDO other = (ApplicationTDO) obj;
        if (!Objects.equals(this.appName, other.appName)) {
            return false;
        }
        if (!Objects.equals(this.appID, other.appID)) {
            return false;
        }
        if (!Objects.equals(this.application, other.application)) {
            return false;
        }
        return true;
    }

}
