package ca2.model;

import java.io.Serializable;
import javax.persistence.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andy
 */
@Entity
public class Users implements Serializable{
    
     private static final long serialVersionUID = 1L;
     
     private String userid;
     private String password;

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
     
     
}
