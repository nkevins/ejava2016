package epod.business;

import epod.model.Delivery;
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
    
}
