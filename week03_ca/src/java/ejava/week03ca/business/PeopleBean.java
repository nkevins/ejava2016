/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.week03ca.business;

import ejava.week03ca.model.People;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author andy
 */
@Stateless
public class PeopleBean {
    @PersistenceContext private EntityManager em;
    
    public void save(People people) {
	em.persist(people);
    }
    
    public Optional<People> getByEmail(String email) {
        String queryString = "select p from People p where p.email = :email";
        TypedQuery<People> query = em.createQuery(queryString, People.class);
        query.setParameter("email", email);
        
        List<People> people = query.getResultList();
        if (people.isEmpty()) {
            return Optional.empty();
        } else {
            return (Optional.ofNullable(people.get(0)));
        }
    }
}
