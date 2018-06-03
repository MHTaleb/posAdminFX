/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.dto.pack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author taleb
 */
public class PackForm {
    private Long id;
    private List<Long> packApplications;
    private String packName;

    public void setPackApplications(List<Long> packApplications) {
        this.packApplications = packApplications;
    }

    public List<Long> getPackApplications() {
        return packApplications;
    }

    public PackForm() {
    }

    public PackForm(Long id, List<Long> packApplications, String packName) {
        this.id = id;
        this.packApplications = packApplications;
        this.packName = packName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public PackForm(List<Long> packApplications, String packName) {
        this.packApplications = packApplications;
        this.packName = packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getPackName() {
        return packName;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PackForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    
    
}
