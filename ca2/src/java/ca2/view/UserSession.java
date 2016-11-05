package ca2.view;

import java.io.Serializable;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class UserSession implements Serializable {
    @Inject private Principal user;

    public String getUsername() {
            return (user.getName());
    }
}
