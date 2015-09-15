/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edit_useror.
 */
package com.onfd.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onfd.security.OnfdAuthenticationSuccessHandler;

/**
 *
 * @author Aleksey Malyshev
 */
@Controller
@RequestMapping("/")
public class Root {

    private static final Logger _log;

    @Autowired
    OnfdAuthenticationSuccessHandler authenticationHandler;

    static {
        _log = Logger.getLogger(Root.class.getName());
    }

    @Secured({"ROLE_CUSTOMER", "ROLE_VENDOR"})
    @RequestMapping(method=RequestMethod.GET)
    public void rootGet(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	authenticationHandler.onAuthenticationSuccess(request, response, authentication);
    }

}
