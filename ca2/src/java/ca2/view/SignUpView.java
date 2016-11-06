/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.view;

import ca2.business.UserBean;
import ca2.model.Group;
import ca2.model.User;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author andy
 */
@Named
@RequestScoped
public class SignUpView {
    
    private String userId;
    private String password;
    private User user = new User();
    private Group group = new Group();
    private String message = "";
    private static String AUTH = "authenticated";
    @EJB private UserBean userBean;


    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void add(){
        
        try{
            Optional<User> userRegister = userBean.find(userId);
            
             if (userRegister.isPresent()) {
                 setMessage("User Id already exists in the system. Please enter the other User Id");
                 return ;
             }
            String hashPwd = user.encodePassword(this.password);
            user.setUserid(userId);
            user.setPassword(hashPwd);
           
            group.setGroupid(AUTH);
            group.setUserid(userId);
            
            userBean.save(user,group);
            
            setMessage("New user created successfully. Please click Login link to login into system");
           
        }catch(Exception ex){
            setMessage("Failed to register users");
            System.out.println(ex);
        }
        
        
        //System.out.println(hashPwd);
        
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
