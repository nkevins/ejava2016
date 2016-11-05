package ca2.view;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class NoteCategory {
    
    private List<String> noteCategory;
    
    @PostConstruct
    private void init() {
        noteCategory = new LinkedList<>();
        noteCategory.add("Social");
        noteCategory.add("For Sale");
        noteCategory.add("Jobs");
        noteCategory.add("Tuition");
    }
    
    public List<String> noteCategories() { return (noteCategory); }
}
