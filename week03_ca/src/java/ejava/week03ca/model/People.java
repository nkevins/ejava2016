package ejava.week03ca.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class People {
    
    @Id
    @Column(name = "pid")
    private Integer peopleId;
    
    private String name;
    private String email;
    
    @OneToMany(mappedBy = "people")
    private List<Appointment> appointments;

    public Integer getPeopleId() {
        return peopleId;
    }
    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    
}
