package ca2.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class NoteRoom {

    private Map<String, List<Session>> rooms = new HashMap<>();
    
    public void add(String category, Session session) {
        List<Session> allSessions = rooms.computeIfAbsent(category, s -> new LinkedList<>());
        allSessions.add(session);
    }
    
    public void broadcast(String category, String text) {
        // Broadcast to All categories room
        if (rooms.containsKey("All")) {
            rooms.get("All").stream().forEach(s -> {
                try {
                    s.getBasicRemote().sendText(text);
                } catch (IOException ex) { 
                    remove(category, s);
                }
            });
        }
        
        // Broadcast to the categorized rooms
        if (rooms.containsKey(category)) {
            rooms.get(category).stream().forEach(s -> {
                try {
                    s.getBasicRemote().sendText(text);
                } catch (IOException ex) { 
                    remove(category, s);
                }
            });
        }
    }
    
    public void remove(String category, Session session) {
        if (rooms.containsKey(category)) {
            rooms.get(category).remove(session);
        }
    }
}
