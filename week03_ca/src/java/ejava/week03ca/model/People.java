package ejava.week03ca.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class People implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "pid")
    private String personId;
    
    private String name;
    private String email;
    
    @OneToMany(mappedBy = "people")
    private List<Appointment> appointments;

    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
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
    
    public People setPeople(String name, String email){
        People p = new People();
        p.setName(name);
        p.setEmail(email);
        p.setPersonId(UUID.randomUUID().toString().substring(0, 8));
        return p;
    }
    
    public JsonObject toJSON() {        
        return (Json.createObjectBuilder()
                .add("name", name)
                .add("email", email)
                .build());
    }
    
}
