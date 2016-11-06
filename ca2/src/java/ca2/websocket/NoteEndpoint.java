package ca2.websocket;

import ca2.business.NoteBean;
import ca2.model.Note;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@RequestScoped
@ServerEndpoint("/note/{category}")
public class NoteEndpoint {
    
    @EJB private NoteBean noteBean;
    @Inject private NoteRoom rooms;
    
    @OnOpen
    public void open(Session sess, @PathParam("category") String category) {
        rooms.add(category, sess);
        List<Note> notes = noteBean.getAll(category);
        
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Note n : notes) {
            builder.add(n.toJson());
        }
        
        try {
            sess.getBasicRemote().sendText(builder.build().toString());
        } catch (IOException ex) {
            try { sess.close(); } catch (IOException e) {}
        }
    }
    
    @OnMessage
    public void message(String text, @PathParam("category") String category) {
        rooms.broadcast(category, text);
    }
    
    @OnClose
    public void close(Session session, @PathParam("category") String category) {
        rooms.remove(category, session);
    }
}
