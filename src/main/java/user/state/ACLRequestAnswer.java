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
public class ACLRequestAnswer {

    private String message;

    private List<ACLContent> content;

    private String sequence;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setContent(List<ACLContent> content) {
        this.content = content;
    }

    public List<ACLContent> getContent() {
        return this.content;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSequence() {
        return this.sequence;
    }
}
