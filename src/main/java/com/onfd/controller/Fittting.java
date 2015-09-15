/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edit_useror.
 */
package com.onfd.controller;

import com.onfd.controller.errors.InvalidIdException;
import com.onfd.model.Customer;
import com.onfd.model.Measurement;
import com.onfd.model.Product;
import com.onfd.model.ProductSize;
import com.onfd.model.SessionManager;
import com.onfd.security.UserContainer;

import java.util.List;
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
@SessionAttributes({"user", "psize"})
@RequestMapping("/customer/fitting")
public class Fittting {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(Shop.class.getName());
    }

    private Customer getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    @Secured("ROLE_CUSTOMER")
    @RequestMapping(value="/{psid}", method = RequestMethod.GET)
    public String fittingGet(@PathVariable("psid") Integer id, Model model) {
        Customer user = getCurrentUser();
        ProductSize psize = sessionManager.load(ProductSize.class, id);
        if (psize == null) {
            throw new InvalidIdException();
        }
        model.addAttribute("psize", psize);
        model.addAttribute("user", user);
        return "customer/fitting";
    }

}
