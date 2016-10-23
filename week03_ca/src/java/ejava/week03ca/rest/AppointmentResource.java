package ejava.week03ca.rest;

import ejava.week03ca.task.FindAppointmentTask;
import ejava.week03ca.business.AppointmentBean;
import ejava.week03ca.business.PeopleBean;
import ejava.week03ca.task.CreateAppointmentTask;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @EJB private AppointmentBean appointmentBean;
    @EJB private PeopleBean peopleBean;
    
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
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void createAppointment(MultivaluedMap<String, String> formData, 
            @Suspended AsyncResponse async) {
        
        String description = formData.getFirst("description");
	Date appointmentDate = new Date(Long.parseLong(formData.getFirst("date")));
        String email = formData.getFirst("email");
        
        CreateAppointmentTask task = new CreateAppointmentTask();
        task.setAppointment(appointmentBean, peopleBean, appointmentDate, description, email);
        task.setAsyncResponse(async);
        
        executors.execute(task);
    }
    
}
