/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author taleb
 */
public class EtatLoginPojo {
    private boolean active;

    public EtatLoginPojo() {
    }

    public EtatLoginPojo(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "EtatLoginPojo{" + "active=" + active + '}';
    }
    
    
    
}
