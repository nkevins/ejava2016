package epod.business;

import epod.model.Pod;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@Stateless
public class PodBean {
    
    @PersistenceContext private EntityManager em;
    
    public void uploadPod(Integer podId, String note, byte[] img) {
        Pod pod = em.find(Pod.class, podId);
        pod.setNote(note);
        pod.setImage(img);
        pod.setDeliveryDate(new Date());
        
        em.persist(pod);
        
        notifyHq(pod);
    }
    
    public void updatePodAckId(Integer podId, String ackId) {
        Pod pod = em.find(Pod.class, podId);
        pod.setAckId(ackId);
        
        em.persist(pod);
    }
    
    public void notifyHq(Pod pod) {
        Client client = ClientBuilder.newBuilder()
			.register(MultiPartFeature.class)
			.build();

	byte[] buffer = pod.getImage();
	BodyPart imgPart = new BodyPart(buffer, MediaType.APPLICATION_OCTET_STREAM_TYPE);

	//This the the form field name
	//<input type="file" name="image">
	imgPart.setContentDisposition(
			FormDataContentDisposition.name("image")
			.fileName("ca3.png").build());

	//Add other fields
	MultiPart formData = new FormDataMultiPart()
			.field("teamId", "a85da9ab", MediaType.TEXT_PLAIN_TYPE)
                        .field("podId", pod.getPodId(), MediaType.TEXT_PLAIN_TYPE)
			.field("callback", "http://10.10.24.207:8080/epod/callback", MediaType.TEXT_PLAIN_TYPE)
                        .field("note", pod.getNote(), MediaType.TEXT_PLAIN_TYPE)
			.bodyPart(imgPart);
	formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

	WebTarget target = client.target("http://10.10.0.48:8080/epod/upload");
	Invocation.Builder inv = target.request();

	Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

	System.out.println(">> call resp:" + callResp.getStatus());
    }
    
}
