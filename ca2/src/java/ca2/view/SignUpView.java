/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author andy
 */
@Named
@RequestScoped
public class SignUpView {
    
    private String userId;
    private String password;

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void add(){
        
    }
    
}
