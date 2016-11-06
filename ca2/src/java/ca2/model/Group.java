/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable{
    
    @Id
    private String groupid;
    private String userid;

    public String getGroupid() {
        return groupid;
    }

    public String getUserid() {
        return userid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    

}
