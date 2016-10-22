/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.week03ca.task;

import ejava.week03ca.business.PeopleBean;
import ejava.week03ca.model.People;
import java.util.Optional;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author andy
 */
public class FindPeopleTask implements Runnable{

    private PeopleBean peopleBean;
    private String email;
    private AsyncResponse async;
        
    @Override
    public void run() {
     
        Optional<People> people = peopleBean.getByEmail(email);
        
        if (people.isPresent()) {
            People p = people.get();
             async.resume(Response.ok(p.toJSON()).build());
            
        }
         async.resume(Response.status(Response.Status.NOT_FOUND)
            .build());
      
    }
    
    public void setSearchCriteria(String email, PeopleBean peopleBean){
        this.email = email;
        this.peopleBean = peopleBean;
    }
    
     public void setAsyncResponse(AsyncResponse async){
        this.async = async;
    }
}
