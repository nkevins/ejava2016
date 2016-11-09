/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.view;

import ca2.business.NoteBean;
import ca2.model.Note;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andy
 */
@Named
@RequestScoped
public class NoteView {
    
    @EJB private NoteBean noteBean;
    @Inject private UserSession userSession;
    
    private List<Note> noteList = null;
    
    public List<Note> getNoteList() {
        return noteList;
    }
    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    } 
    
    @PostConstruct
    public void init() {
        noteList= noteBean.getNoteByUserId(userSession.getUsername());
    }       
}
