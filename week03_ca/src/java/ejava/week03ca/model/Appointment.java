package ejava.week03ca.model;

import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
    
    @Id
    @Column(name = "appt_id")
    private Integer appointmentId;
    
    private String description;
    
    @Column(name = "appt_date")
    private Date appointmentDate;
    
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private People people;

    public Integer getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public People getPeople() {
        return people;
    }
    public void setPeople(People people) {
        this.people = people;
    }
    
    public JsonObject toJSON() {        
        return (Json.createObjectBuilder()
                .add("appointmentId", appointmentId)
                .add("dateTime", appointmentDate.toString())
                .add("description", description)
                .add("personId", people.getPeopleId())
                .build());
    }
    
}
