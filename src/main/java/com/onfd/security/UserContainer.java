/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.security;

import com.onfd.controller.errors.UserNotLoggerException;
import com.onfd.model.User;
import java.io.Serializable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Aleksey Malyshev
 */
public class UserContainer implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    private User currentUser;

    @SuppressWarnings("unchecked")
    public <T> T getCurrentUser() {
        if (currentUser == null) {
            SecurityContext context = SecurityContextHolder.getContext();
            if (context == null) {
                throw new UserNotLoggerException("Security Context is null.");
            }
            OnfdUserDetails ud = (OnfdUserDetails)context.getAuthentication().getPrincipal();
            if (ud == null) {
                throw new UserNotLoggerException("User Details is null.");
            }
            currentUser = ud.getUser();
        }
        return (T)currentUser;
    }

    public <T> void setCurrentUser(T user) {
        this.currentUser = (User)user;
    }

}
