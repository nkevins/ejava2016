package ejava.week03ca.rest;

import ejava.week03ca.business.AppointmentBean;
import ejava.week03ca.model.Appointment;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Integer id) {
        Optional<Appointment> apt = appointmentBean.getById(id);
        
        if (apt.isPresent())
            return (Response.ok(apt.get().toJSON())
                    .build());

        return (Response.status(Response.Status.NOT_FOUND)
            .entity("Appointment id not found:" + id)
            .build());
    }
    
}
