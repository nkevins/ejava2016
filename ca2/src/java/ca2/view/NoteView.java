/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.view;

import ca2.business.NoteBean;
import ca2.business.UserBean;
import ca2.model.Note;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author andy
 */
@Named
@RequestScoped
public class NoteView {
    
    private List<Note> noteList = null;
    @EJB private NoteBean noteBean;
    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
    
    public String show(String userId){
       noteList= noteBean.getNoteByUserId(userId);
        return "showNote";
    }
    
}
