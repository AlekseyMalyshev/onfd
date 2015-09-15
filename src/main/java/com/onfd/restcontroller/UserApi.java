/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.restcontroller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onfd.model.Customer;
import com.onfd.model.SessionManager;
import com.onfd.model.User;
import com.onfd.security.UserContainer;

@RestController
@RequestMapping("/rest/user")
public class UserApi {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(UserApi.class.getName());
    }

    private Customer getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    @Secured({"ROLE_CUSTOMER", "ROLE_VENDOR"})
    @RequestMapping(method=RequestMethod.GET)
    public Customer customerGet() {
        return getCurrentUser();
    }

    @Secured({"ROLE_CUSTOMER", "ROLE_VENDOR"})
    @RequestMapping(method=RequestMethod.POST)
    public void customerPost(@RequestBody User user) {
        //sessionManager.save(user);
        // TODO: 
    }

    @Secured({"ROLE_CUSTOMER", "ROLE_VENDOR"})
    @RequestMapping(method=RequestMethod.DELETE)
    public void customerDelete() {
        sessionManager.delete(getCurrentUser());
    }

}
