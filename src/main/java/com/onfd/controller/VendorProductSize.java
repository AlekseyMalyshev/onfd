/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.controller;

import com.onfd.security.UserContainer;
import com.onfd.model.SessionManager;
import com.onfd.controller.errors.InvalidIdException;
import com.onfd.model.Product;
import com.onfd.model.ProductSize;
import com.onfd.model.Vendor;
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
@SessionAttributes("psize")
@Secured("ROLE_VENDOR")
@RequestMapping("/vendor/product/size")
public class VendorProductSize {

    private static final Logger _log;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    @Lazy
    private UserContainer userContainer;

    static {
        _log = Logger.getLogger(VendorProductSize.class.getName());
    }

    private Vendor getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    /**
     * Prepare view for editing a product size.
     * 
     * @param pid Product id
     * @param name Product size id.
     * @param model
     * @return 
     */
    @RequestMapping(value="/{pid}/{name}", method = RequestMethod.GET)
    public String sizeEditGet(@PathVariable("pid") Integer pid,
            @PathVariable("name") String name, Model model) {
        Vendor user = getCurrentUser();
        Product product = user.getProduct(pid);
        if (product == null) {
            throw new InvalidIdException();
        }
        ProductSize size = product.getSize(name);
        if (size == null) {
            throw new InvalidIdException();
        }
        model.addAttribute("psize", size);
        return getProductPage(product);
    }

    /**
     * Delete a size definition.
     * 
     * @param pid Product id
     * @param name Product size id
     * @param model
     * @return 
     */
    @RequestMapping(value="/delete/{pid}/{name}", method = RequestMethod.GET)
    public String sizeDeleteGet(@PathVariable("pid") Integer pid,
            @PathVariable("name") String name, Model model) {
        Vendor user = getCurrentUser();
        Product product = user.getProduct(pid);
        if (product == null) {
            throw new InvalidIdException();
        }
        ProductSize size = product.getSize(name);
        if (size == null) {
            throw new InvalidIdException();
        }
        sessionManager.delete(size);
        product.getSizes().remove(size);

        return "redirect:/vendor";
    }

    /**
     * Process new or edit size.
     * 
     * @param size
     * @param result
     * @param model
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public String sizePost(@Valid @ModelAttribute("psize") ProductSize size,
            BindingResult result, Model model) {

        Product product = size.getProduct();
        if(result.hasErrors()) {
            model.addAttribute("error", "true");
            return getProductPage(product);
        }

        if (size.isNew()) {
            sessionManager.save(size);
            product.addSize(size);
        }
        else {
            sessionManager.update(size);
        }

        return "redirect:/vendor";
    }

    private String getProductPage(Product product) {
        return "vendor/size_" + product.getType().name().toLowerCase();
    }

}
