/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.week03ca.rest;

import ejava.week03a.task.PeopleTask;
import ejava.week03ca.business.PeopleBean;
import ejava.week03ca.model.People;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author andy
 */
@RequestScoped
@Path("/people")
public class PeopleResouce {
    
    @EJB private PeopleBean peopleBean;
    People people = new People();
    @PersistenceContext private EntityManager em;
     
    @Resource (mappedName="concurrent/appointmentThreadPool")
    private ManagedExecutorService executors;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void createPeople(MultivaluedMap<String, String> formData, @Suspended AsyncResponse async) {
        
        String name = formData.getFirst("name");
	String email = formData.getFirst("email");
        PeopleTask pplTask = new PeopleTask();
        pplTask.setPeople(name, email, peopleBean);
        pplTask.setAsyncResponse(async);
        executors.execute(pplTask);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeople(@QueryParam("email") String email) {
        
        Optional<People> people = peopleBean.getByEmail(email);
        
        if (people.isPresent()) {
            People p = people.get();
            return Response.ok(p.toJSON()).build();
        }
        
        return (Response.status(Response.Status.NOT_FOUND)
            .build());
    }
    
}
