package epod.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Pod implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pod_id")
    private Integer podId;
   
    private String note;
    private byte[] image;
    
    @Column(name="delivery_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date deliveryDate;
    
    @Column(name="ack_id")
    private String ackId;
        
    @OneToOne
    @JoinColumn(name="pkg_id", referencedColumnName="pkg_id")
    private Delivery delivery;

    public Integer getPodId() {
        return podId;
    }
    public void setPodId(Integer podId) {
        this.podId = podId;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getAckId() {
        return ackId;
    }
    public void setAckId(String ackId) {
        this.ackId = ackId;
    }    

    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }   
    
}
