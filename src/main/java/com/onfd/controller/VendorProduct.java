/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.controller;

import com.onfd.security.UserContainer;
import com.onfd.model.SessionManager;
import com.onfd.controller.errors.InvalidIdException;
import com.onfd.model.Manufacturer;
import com.onfd.model.Product;
import com.onfd.model.Vendor;
import java.util.List;
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
@SessionAttributes({"product", "manuf"})
@Secured("ROLE_VENDOR")
@RequestMapping("/vendor/product")
public class VendorProduct {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(VendorProduct.class.getName());
    }

    private Vendor getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String productAddGet(Model model) {
        List<Manufacturer> manufs = sessionManager.query(Manufacturer.getAllQuery());
        model.addAttribute("manufs", manufs);
        model.addAttribute("manuf", new Manufacturer());
        return "vendor/select_manuf";
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String productEditGet(@PathVariable("id") Integer id, Model model) {
        Vendor user = getCurrentUser();
        Product product = user.getProduct(id);
        if (product == null) {
            throw new InvalidIdException();
        }
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "vendor/product";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String productDeleteGet(@PathVariable("id") Integer id, Model model) {
        Vendor user = getCurrentUser();
        Product product = user.getProduct(id);
        if (product == null) {
            throw new InvalidIdException();
        }
        sessionManager.delete(product);
        user.getProducts().remove(product);

        return "redirect:/vendor";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String productAddPost(@Valid @ModelAttribute("manuf") Manufacturer manuf,
            BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("error", "true");
            return "vendor/select_manuf";
        }

        if (manuf.isNew()) {
            manuf.setVendor(getCurrentUser());
            sessionManager.save(manuf);
        }
        else {
            manuf = sessionManager.getOne(
                    Manufacturer.getByIdQuery(), manuf.getId());
        }

        Product product = new Product();
        product.setManufacturer(manuf);
        model.addAttribute("product", product);
        model.addAttribute("user", getCurrentUser());
        return "vendor/product";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String productPost(@Valid @ModelAttribute("product") Product product,
            BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("error", "true");
            return "vendor/product";
        }

        if (product.isNew()) {
            Vendor user = getCurrentUser();
            user.addProduct(product);
            sessionManager.save(product);
        }
        else {
            sessionManager.update(product);
        }
        return "redirect:/vendor";
    }

}
