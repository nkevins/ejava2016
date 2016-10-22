/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.week03a.task;

import ejava.week03ca.rest.*;
import ejava.week03ca.business.PeopleBean;
import ejava.week03ca.model.People;
import java.math.BigDecimal;
import javax.ws.rs.container.AsyncResponse;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
/**
 *
 * @author andy
 */
public class PeopleTask implements Runnable{

    private PeopleBean peopleBean;
    private People people = new People();
    private People p = null;
    private AsyncResponse async;
    
    @Override
    public void run() {
     
        peopleBean.save(p);
        
         JsonObject json = Json.createObjectBuilder()
                .add("name", p.getName())
                .add("email", p.getEmail())
                 .add("personId",p.getPeopleId() )
                .build();
        System.out.println(String.format(">> name: %s, email: %s", p.getName(), p.getEmail()));
        async.resume(Response.status(Response.Status.CREATED)
                .entity(json)
                .build());
    }
    
    public void setPeople(String name, String email, PeopleBean peopleBean){
         p = people.setPeople(name,email);
         this.peopleBean = peopleBean;
    }
    
    public void setAsyncResponse(AsyncResponse async){
        this.async = async;
    }
}
