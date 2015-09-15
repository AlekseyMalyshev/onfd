/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Aleksey Malyshev
 */
@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="User is not logged in.")
public class UserNotLoggerException extends AuthenticationException {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public UserNotLoggerException(String message) {
        super(message);
    }

    public UserNotLoggerException(String message, Throwable cause) {
        super(message, cause);
    }
}
