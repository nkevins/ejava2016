package ca2.business;

import ca2.model.Group;
import ca2.model.User;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserBean {
    
    @PersistenceContext private EntityManager em;
    
    public Optional<User> find(String userid) {
        return Optional.ofNullable(em.find(User.class, userid));
    }
    
    public void save(User User,Group group) {
	em.persist(User);
        em.persist(group);
    }
}
