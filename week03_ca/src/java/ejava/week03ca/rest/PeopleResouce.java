/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.week03ca.rest;

import ejava.week03ca.business.PeopleBean;
import ejava.week03ca.model.People;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    //@Inject private People people;
    People people = new People();
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPeople(MultivaluedMap<String, String> formData) {
        
        String name = formData.getFirst("name");
	String email = formData.getFirst("email");
        
        People p = people.setPeople(name,email);
        peopleBean.save(p);
        
	JsonObject json = Json.createObjectBuilder()
				.add("name", name)
				.add("email", email)
				.build();
	System.out.println(String.format(">> name: %s, email: %s", name, email));
        return (Response.status(Response.Status.CREATED)
				.entity(json)
				.build());
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
