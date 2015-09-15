/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edit_useror.
 */
package com.onfd.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class Login {

    private static final Logger _log;

    static {
        _log = Logger.getLogger(Login.class.getName());
    }

    @RequestMapping(method=RequestMethod.GET)
    public String loginGet() {
        return ("login");
    }

}
