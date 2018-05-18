/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import application.data.GenericFormDTO;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author taleb
 */

public class ComponentForm extends GenericFormDTO {

    private String app_name;
    
    private List<Long> id_fils;
    private List<Long> id_parents;
    private List<Long> id_datas;

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public List<Long> getId_fils() {
        return id_fils;
    }

    public void setId_fils(List<Long> id_fils) {
        this.id_fils = id_fils;
    }

    public List<Long> getId_parents() {
        return id_parents;
    }

    public void setId_parents(List<Long> id_parents) {
        this.id_parents = id_parents;
    }

    public List<Long> getId_datas() {
        return id_datas;
    }

    public void setId_datas(List<Long> id_datas) {
        this.id_datas = id_datas;
    }

    public ComponentForm(String app_name, List<Long> id_fils, List<Long> id_parents, List<Long> id_datas) {
        this.app_name = app_name;
        this.id_fils = id_fils;
        this.id_parents = id_parents;
        this.id_datas = id_datas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.app_name);
        hash = 89 * hash + Objects.hashCode(this.id_fils);
        hash = 89 * hash + Objects.hashCode(this.id_parents);
        hash = 89 * hash + Objects.hashCode(this.id_datas);
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
        final ComponentForm other = (ComponentForm) obj;
        if (this.app_name.toLowerCase().equals( other.app_name.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.id_fils, other.id_fils)) {
            return false;
        }
        if (!Objects.equals(this.id_parents, other.id_parents)) {
            return false;
        }
        return Objects.equals(this.id_datas, other.id_datas);
    }

    public ComponentForm() {
    }
    
    
    
}
