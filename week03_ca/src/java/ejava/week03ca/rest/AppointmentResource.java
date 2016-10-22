package ejava.week03ca.rest;

import ejava.week03ca.task.FindAppointmentTask;
import ejava.week03ca.business.AppointmentBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @EJB private AppointmentBean appointmentBean;
    
    @Resource(mappedName = "concurrent/appointmentThreadPool")
    private ManagedExecutorService executors;
    
    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void get(@PathParam("email") String email, 
            @Suspended AsyncResponse async) {
        
        FindAppointmentTask task = new FindAppointmentTask();
        task.setSearchCrietria(email, appointmentBean);
        task.setAsyncResponse(async);
        
        executors.execute(task);
    }
    
}
