package epod.business;

import epod.model.Pod;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PodBean {
    
    @PersistenceContext private EntityManager em;
    
    public void uploadPod(Integer podId, String note, byte[] img) {
        Pod pod = em.find(Pod.class, podId);
        pod.setNote(note);
        pod.setImage(img);
        pod.setDeliveryDate(new Date());
        
        em.persist(pod);
    }
    
}
