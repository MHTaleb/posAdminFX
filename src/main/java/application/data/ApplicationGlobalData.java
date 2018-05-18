/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.data;

import pojo.LoginPojo;

/**
 *
 * @author taleb
 */
public class ApplicationGlobalData {
    
    public static final String SERVER_URL="https://localhost:8443/";
    
    private  static LoginPojo loginPojo;

    public static void setLoginPojo(LoginPojo loginPojo) {
        ApplicationGlobalData.loginPojo = loginPojo;
    }

    public static LoginPojo getLoginPojo() {
        return loginPojo;
    }
    
    
    
}
