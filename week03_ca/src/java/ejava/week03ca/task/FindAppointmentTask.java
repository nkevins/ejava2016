package ejava.week03ca.task;

import ejava.week03ca.business.AppointmentBean;
import ejava.week03ca.model.Appointment;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

public class FindAppointmentTask implements Runnable {

    private String email;
    private AppointmentBean appointmentBean;
    private AsyncResponse async;
    
    public void setSearchCrietria(String email, AppointmentBean appointmentBean) {
        this.email = email;
        this.appointmentBean = appointmentBean;
    }
    
    public void setAsyncResponse(AsyncResponse async) {
        this.async = async;
    }
    
    @Override
    public void run() {
        List<Appointment> apt = appointmentBean.getByEmail(email);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        
        for (Appointment a : apt) {
            arrBuilder.add(a.toJSON());
        }
        
        async.resume(Response.ok(arrBuilder.build()).build());       
    }
    
}
