/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.controller;

import com.onfd.security.UserContainer;
import com.onfd.model.SessionManager;
import com.onfd.model.User;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@Secured({"ROLE_CUSTOMER", "ROLE_VENDOR"})
@RequestMapping("/{type:customer|vendor}/edit")
public class UserEdit {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(UserEdit.class.getName());
    }

    private User getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    @RequestMapping(method=RequestMethod.GET)
    public String editGet(@PathVariable("type") String type, Model model) {
        model.addAttribute("user", getCurrentUser());
        return type + "/edit";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String editPost(@PathVariable("type") String type,
        @Valid @ModelAttribute("user") User user,
        BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("error", "true");
        }
        else {
            sessionManager.update(user);
        }
        return type + "/edit";
    }

}
