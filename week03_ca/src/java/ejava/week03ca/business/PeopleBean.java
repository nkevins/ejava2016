/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.week03ca.business;

import ejava.week03ca.model.People;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
