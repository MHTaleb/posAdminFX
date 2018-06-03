/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdo.pack;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import rest.dto.pack.PackContent;

/**
 *
 * @author taleb
 */
public class PackTDO {

    private final LongProperty packID = new SimpleLongProperty();
   

    public long getPackID() {
        return packID.get();
    }

    public void setPackID(long value) {
        packID.set(value);
    }

    public LongProperty packIDProperty() {
        return packID;
    }
    private final StringProperty packName = new SimpleStringProperty();

    public String getPackName() {
        return packName.get();
    }

    public void setPackName(String value) {
        packName.set(value);
    }

    public StringProperty packNameProperty() {
        return packName;
    }
    private final ObjectProperty<PackContent> pack = new SimpleObjectProperty<>();

    public PackContent getPack() {
        return pack.get();
    }

    public void setPack(PackContent value) {
        pack.set(value);
    }

    public ObjectProperty packProperty() {
        return pack;
    }
    private final BooleanProperty checkedPack = new SimpleBooleanProperty();

    public boolean isCheckedPack() {
        return checkedPack.get();
    }

    public void setCheckedPack(boolean value) {
        checkedPack.set(value);
    }

    public BooleanProperty checkedPackProperty() {
        return checkedPack;
    }

    
  
}
