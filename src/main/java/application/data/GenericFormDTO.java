/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.data;

import app.ApplicationFormAddController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taleb
 */
public class GenericFormDTO {

     @Override
        public String toString() {
            try {
                return new ObjectMapper().writeValueAsString(this);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(ApplicationFormAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    
}
