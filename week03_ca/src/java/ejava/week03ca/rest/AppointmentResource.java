package ejava.week03ca.rest;

import ejava.week03ca.business.AppointmentBean;
import ejava.week03ca.model.Appointment;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @EJB private AppointmentBean appointmentBean;
    
    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("email") String email) {
        List<Appointment> apt = appointmentBean.getByEmail(email);
        
        if (apt.size() == 0) {
            return (Response.status(Response.Status.NOT_FOUND)
            .build());
        }

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Appointment a : apt) {
            arrBuilder.add(a.toJSON());
        }
        
        return (Response.ok(arrBuilder.build()).build());
    }
    
}
