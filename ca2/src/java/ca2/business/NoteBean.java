package ca2.business;

import ca2.model.Note;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class NoteBean {
    
    @PersistenceContext private EntityManager em;
    
    public void save(Note note) {
        em.persist(note);
    }
    
    public List<Note> getAll(String category) {
        TypedQuery<Note> query;
        if (category.equals("All")) {
            query = em.createQuery("select n from Note n"
                , Note.class);
        } else {
            query = em.createQuery("select n from Note n where (n.category = :category)"
                , Note.class);
            query.setParameter("category", category);
        }
                
        return (query.getResultList());
    }
}
