package ejava.week03ca.business;

import ejava.week03ca.model.Appointment;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AppointmentBean {
    
    @PersistenceContext private EntityManager em;
    
    public Optional<Appointment> getById(int id) {
        return Optional.ofNullable(em.find(Appointment.class, id));
    }
    
}
