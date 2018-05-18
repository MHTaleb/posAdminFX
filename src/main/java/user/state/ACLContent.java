/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.state;

import java.util.List;

/**
 *
 * @author taleb
 */
public class ACLContent
{
    private int id;

    private String aclTitle;

    private List<String> roles;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setAclTitle(String aclTitle){
        this.aclTitle = aclTitle;
    }
    public String getAclTitle(){
        return this.aclTitle;
    }
    public void setRoles(List<String> roles){
        this.roles = roles;
    }
    public List<String> getRoles(){
        return this.roles;
    }

    @Override
    public String toString() {
        return "ACLContent{" + "id=" + id + ", roleTitle=" + aclTitle + ", roles=" + roles + '}';
    }
    
}
