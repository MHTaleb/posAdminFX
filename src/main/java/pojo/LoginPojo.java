/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author taleb
 */


public class LoginPojo {
    /**
     * us_usrint":1,"us_cextusr":"1","us_nomusr":"Berrehab","us_prnusr":"Abdelrazak","us_datfin":"2010-10-10","us_lastcnx":null,"us_etatusr":{"active":true},"us_applications":[]
     */
    
    public static final String ISO_LOCAL_DATE_PATTERN = "yyyy-MM-dd";
    
    private Long us_usrint;
    private Long us_cextusr;
    private String us_nomusr;
    private String us_prnusr;
    @JsonFormat(shape = Shape.STRING, pattern = ISO_LOCAL_DATE_PATTERN)
    private LocalDate us_datfin;
    @JsonFormat(shape = Shape.STRING, pattern = ISO_LOCAL_DATE_PATTERN)
    private LocalDate us_lastcnx;
    private EtatLoginPojo us_etatusr;
    private List us_applications;

    public Long getUs_usrint() {
        return us_usrint;
    }

    public void setUs_usrint(Long us_usrint) {
        this.us_usrint = us_usrint;
    }

    public Long getUs_cextusr() {
        return us_cextusr;
    }

    public void setUs_cextusr(Long us_cextusr) {
        this.us_cextusr = us_cextusr;
    }

    public String getUs_nomusr() {
        return us_nomusr;
    }

    public void setUs_nomusr(String us_nomusr) {
        this.us_nomusr = us_nomusr;
    }

    public String getUs_prnusr() {
        return us_prnusr;
    }

    public void setUs_prnusr(String us_prnusr) {
        this.us_prnusr = us_prnusr;
    }

    public LocalDate getUs_datfin() {
        return us_datfin;
    }

   
    public void setUs_datfin(String us_datfin) {
        if(us_datfin == null) us_datfin = "2000-01-01";
        this.us_datfin=(LocalDate.parse(us_datfin, DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public LocalDate getUs_lastcnx() {
        return us_lastcnx;
    }

    
    public void setUs_lastcnx(String us_lastcnx) {
        if(us_lastcnx == null) us_lastcnx = "2000-01-01";
        this.us_lastcnx=(LocalDate.parse(us_lastcnx, DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public EtatLoginPojo getUs_etatusr() {
        return us_etatusr;
    }

    public void setUs_etatusr(EtatLoginPojo us_etatusr) {
        this.us_etatusr = us_etatusr;
    }

    public List getUs_applications() {
        return us_applications;
    }

    public void setUs_applications(List us_applications) {
        this.us_applications = us_applications;
    }

    @Override
    public String toString() {
        return "LoginPojo{" + "us_usrint=" + us_usrint + ", us_cextusr=" + us_cextusr + ", us_nomusr=" + us_nomusr + ", us_prnusr=" + us_prnusr + ", us_datfin=" + us_datfin + ", us_lastcnx=" + us_lastcnx + ", us_etatusr=" + us_etatusr + ", us_applications=" + us_applications + '}';
    }

    
    

    
}
