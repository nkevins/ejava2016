package epod.api;

import epod.business.DeliveryBean;
import epod.model.Delivery;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/items")
public class ItemResource {
    
    @EJB private DeliveryBean deliveryBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem() {
        List<Delivery> deliveries = deliveryBean.getAllDelivery();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        
        for (Delivery d : deliveries) {
            arrBuilder.add(d.toJSON());
        }
        
        return Response.ok(arrBuilder.build()).build();
    }
    
}
