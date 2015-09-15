/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onfd.model.Customer;
import com.onfd.model.Measurement;
import com.onfd.model.TShirtSize;
import com.onfd.model.SessionManager;
import com.onfd.security.UserContainer;

@RestController
@RequestMapping("/rest/customer")
public class SizeApi {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(SizeApi.class.getName());
    }

    private Customer getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    @Secured("ROLE_CUSTOMER")
    @RequestMapping(value="/{sid}", method=RequestMethod.GET)
    public List<Object> measuremetGet(@PathVariable("sid") Integer sid) {
    	Customer customer = getCurrentUser();

        List<Object> res = new ArrayList<>();
        res.add(customer.getMeasureDef());
        res.add(sessionManager.load(TShirtSize.class, sid));
        return res;
    }

    @Secured("ROLE_CUSTOMER")
    @RequestMapping(method=RequestMethod.POST)
    public void measuremetPost(@RequestBody Measurement size) {

    }

}
