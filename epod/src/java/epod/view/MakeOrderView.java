/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.view;

import epod.business.DeliveryBean;
import epod.model.Delivery;
import epod.model.Pod;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author andy
 */
@Named
@RequestScoped
public class MakeOrderView {
    
    private String name;
    private String address;
    private String phone;
    private String message;
    @EJB private DeliveryBean bean;
 
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
     public void add(){
         try{
             
             Delivery objDeliver = new Delivery();
             objDeliver.setName(this.name);
             objDeliver.setAddress(this.address);
             objDeliver.setPhone(this.phone);
             
             bean.saveOrder(objDeliver);
             
         }catch(Exception ex){
              setMessage("Failed to submit order");
              System.out.println(ex);
         }
     }
    
}
