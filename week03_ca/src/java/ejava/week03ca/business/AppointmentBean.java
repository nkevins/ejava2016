package ejava.week03ca.business;

import ejava.week03ca.model.Appointment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AppointmentBean {
    
    @PersistenceContext private EntityManager em;
    
    public void save(Appointment apt) {
        em.persist(apt);
    }
    
    public List<Appointment> getByEmail(String email) {
        String queryString = "select a from People p "
                + "join p.appointments a where (p.email = :email)";
        TypedQuery<Appointment> query = em.createQuery(queryString, Appointment.class);
        query.setParameter("email", email);
        
        return query.getResultList();
    }
    
}
