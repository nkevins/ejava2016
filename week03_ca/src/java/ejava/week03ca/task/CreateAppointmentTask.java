package ejava.week03ca.task;

import ejava.week03ca.business.AppointmentBean;
import ejava.week03ca.business.PeopleBean;
import ejava.week03ca.model.Appointment;
import ejava.week03ca.model.People;
import java.util.Date;
import java.util.Optional;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

public class CreateAppointmentTask implements Runnable {

    private AppointmentBean appointmentBean;
    private PeopleBean peopleBean;
    private Date appointmentDate;
    private String description;
    private String email;
    private AsyncResponse async;
    
    public void setAppointment(AppointmentBean appointmentBean,
            PeopleBean peopleBean, Date appointmentDate,
            String description, String email) {
        this.appointmentBean = appointmentBean;
        this.peopleBean = peopleBean;
        this.appointmentDate = appointmentDate;
        this.description = description;
        this.email = email;
    }
    
    public void setAsyncResponse(AsyncResponse async) {
        this.async = async;
    }
    
    @Override
    public void run() {
        Optional<People> ppl = peopleBean.getByEmail(email);
        if (!ppl.isPresent()) {
            async.resume(Response.status(Response.Status.BAD_REQUEST)
                    .build());
        }
        
        Appointment apt = new Appointment();
        apt.setAppointmentDate(appointmentDate);
        apt.setDescription(description);
        apt.setPeople(ppl.get());
        
        appointmentBean.save(apt);
        async.resume(Response.status(Response.Status.CREATED)
                .entity(apt.toJSON())
                .build());
    }
    
}
