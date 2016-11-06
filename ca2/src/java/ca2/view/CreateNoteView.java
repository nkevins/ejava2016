package ca2.view;

import ca2.business.NoteBean;
import ca2.business.UserBean;
import ca2.model.Note;
import ca2.model.User;
import ca2.websocket.NoteRoom;
import java.util.Date;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;

@RequestScoped
@Named
public class CreateNoteView {
    
    @Inject private UserSession userSession;
    @Inject private NoteRoom rooms;
    @EJB private NoteBean noteBean;
    @EJB private UserBean userBean;
    
    private String title;
    private String category;
    private String content;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public String create() {
        Optional<User> ou = userBean.find(userSession.getUsername());
        if (!ou.isPresent()) {
           return "index"; 
        }
        
        Note note = new Note();
        note.setTitle(title);
        note.setCategory(category);
        note.setContent(content);
        note.setCreatedDate(new Date());
        note.setUser(ou.get());
        
        noteBean.save(note);
        
        String jsonString = Json.createArrayBuilder()
                            .add(note.toJson()).build().toString();
        rooms.broadcast(category, jsonString);
        
        return "menu";
    }
}
