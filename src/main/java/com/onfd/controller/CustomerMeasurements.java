/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.controller;

import com.onfd.security.UserContainer;
import com.onfd.model.SessionManager;
import com.onfd.controller.errors.InvalidIdException;
import com.onfd.model.Measurement;
import com.onfd.model.Customer;
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
@SessionAttributes("measure")
@Secured("ROLE_CUSTOMER")
@RequestMapping("/customer/measurements")
public class CustomerMeasurements {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(CustomerMeasurements.class.getName());
    }
    
    private Customer getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String measurementsAddGet(Model model) {
        model.addAttribute("measure", new Measurement());
        model.addAttribute("user", getCurrentUser());
        return "customer/measurements";
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String measurementsEditGet(@PathVariable("id") Integer id, Model model) {
        Customer user = getCurrentUser();
        Measurement measure = user.getMeasurement(id);
        if (measure == null) {
            throw new InvalidIdException();
        }
        model.addAttribute("measure", measure);
        model.addAttribute("user", user);
        return "customer/measurements";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String measurementsDeleteGet(@PathVariable("id") Integer id, Model model) {
        Customer user = getCurrentUser();
        Measurement measure = user.getMeasurement(id);
        if (measure == null) {
            throw new InvalidIdException();
        }
        sessionManager.delete(measure);
        user.getMeasurements().remove(measure);

        return "redirect:/customer";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String measurementsPost(@Valid @ModelAttribute("measure") Measurement measure,
            BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("error", "true");
            return "customer/measurements";
        }
        else if (measure.isNew()) {
            Customer user = getCurrentUser();
            user.addMeasurement(measure);
            sessionManager.save(measure);
        }
        else {
            sessionManager.update(measure);
        }
        return "redirect:/customer";
    }

}
