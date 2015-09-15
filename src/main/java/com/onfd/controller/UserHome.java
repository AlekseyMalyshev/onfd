/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edit_useror.
 */
package com.onfd.controller;

import com.onfd.model.User;
import com.onfd.security.UserContainer;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Aleksey Malyshev
 */
@Controller
@SessionAttributes("user")
@RequestMapping("/{type:customer|vendor}")
public class UserHome {

    private static final Logger _log;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(UserHome.class.getName());
    }
    
    private User getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    @Secured({"ROLE_CUSTOMER", "ROLE_VENDOR"})
    @RequestMapping(method=RequestMethod.GET)
    public String indexGet(@PathVariable("type") String type, Model model) {
        model.addAttribute("user", getCurrentUser());
        return type + "/index";
    }

}
