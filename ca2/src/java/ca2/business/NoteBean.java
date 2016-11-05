package ca2.business;

import ca2.model.Note;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NoteBean {
    
    @PersistenceContext private EntityManager em;
    
    public void save(Note note) {
        em.persist(note);
    }
}
