/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.controller;

import com.onfd.controller.errors.InvalidTypeException;
import com.onfd.model.Customer;
import com.onfd.model.SessionManager;
import com.onfd.model.User;
import com.onfd.model.Vendor;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Aleksey Malyshev
 */
@Controller
@RequestMapping("/{type:customer|vendor}/signup")
public class UserSignup {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    static {
        _log = Logger.getLogger(UserSignup.class.getName());
    }

    @RequestMapping(method=RequestMethod.GET)
    public String signupGet(@PathVariable("type") String type, Model model) {
        User user;
        if (type.compareTo("customer") == 0) {
            user = new Customer();
        }
        else if (type.compareTo("vendor") == 0) {
            user = new Vendor();
        }
        else {
            throw new InvalidTypeException();
        }
        model.addAttribute("user", user);
        return type + "/signup";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String signupPost(@Valid @ModelAttribute("user") Vendor user,
            BindingResult result, Model model) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        sessionManager.save(user);
        return "redirect:/login";
    }

}
