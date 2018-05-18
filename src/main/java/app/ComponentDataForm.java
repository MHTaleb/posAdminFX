/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import application.data.GenericFormDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taleb
 */
public class ComponentDataForm extends GenericFormDTO{

        private String attCode;
        private String attTitle;
        private String attVal;


        public ComponentDataForm() {
        }

        public String getAttCode() {
            return attCode;
        }

        public void setAttCode(String attCode) {
            this.attCode = attCode;
        }

        public String getAttTitle() {
            return attTitle;
        }

        public void setAttTitle(String attTitle) {
            this.attTitle = attTitle;
        }

        public String getAttVal() {
            return attVal;
        }

        public void setAttVal(String attVal) {
            this.attVal = attVal;
        }

       
      
        
    }