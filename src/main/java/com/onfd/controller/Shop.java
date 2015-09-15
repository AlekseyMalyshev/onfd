/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edit_useror.
 */
package com.onfd.controller;

import com.onfd.model.Customer;
import com.onfd.model.Product;
import com.onfd.model.SessionManager;
import com.onfd.security.UserContainer;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Aleksey Malyshev
 */
@Controller
@SessionAttributes({"user", "products"})
@RequestMapping("/customer/shop")
public class Shop {

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
    @RequestMapping(method=RequestMethod.GET)
    public String shopGet(Model model) {
    	Customer customer = getCurrentUser();
    	List<Product> products = sessionManager.query(Product.getAllQuery());
    	for (Product p: products) {
            p.applyMeasurement(customer.getMeasureDef());
    	}
        model.addAttribute("user", customer);
        model.addAttribute("products", products);
        return "customer/shop";
    }

}
