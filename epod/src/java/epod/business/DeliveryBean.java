package epod.business;

import epod.model.Delivery;
import epod.model.Pod;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class DeliveryBean {
    
    @PersistenceContext private EntityManager em;
    
    public List<Delivery> getAllDelivery() {
        String queryString = "select d from Delivery d";
        TypedQuery<Delivery> query = em.createQuery(queryString, Delivery.class);
        
        return query.getResultList();
    }
    
    public void saveOrder(Delivery objDeliver){
        em.persist(objDeliver);
        em.flush();
        

        //Delivery del = em.find(Delivery.class, objDeliver.getPackageId());
        
        Pod pod = new Pod();
        objDeliver.setPod(pod);
        
        em.persist(objDeliver);
        em.flush();
        
       // em.refresh(objDeliver);
        //em.persist(objPod);
    }
}
