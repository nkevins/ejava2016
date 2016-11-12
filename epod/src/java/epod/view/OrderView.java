/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.view;

import epod.business.DeliveryBean;
import epod.model.Delivery;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author andy
 */
@Named
@RequestScoped
public class OrderView {
    
    @EJB private DeliveryBean bean;
    List<Delivery> lst;

    public DeliveryBean getBean() {
        return bean;
    }

    public List<Delivery> getLst() {
        return lst;
    }

    public void setBean(DeliveryBean bean) {
        this.bean = bean;
    }

    public void setLst(List<Delivery> lst) {
        this.lst = lst;
    }
    
     @PostConstruct
    public void init() {
        lst= bean.getAllDelivery();
    }   
    public void viewOrder(){
        this.lst = bean.getAllDelivery();
    }
}
