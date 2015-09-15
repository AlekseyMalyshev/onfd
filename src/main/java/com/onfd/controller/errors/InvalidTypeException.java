/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Aleksey Malyshev
 */
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Unexpected rrror")
public class InvalidTypeException extends RuntimeException {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public InvalidTypeException() {
        super();
    }

    public InvalidTypeException(String message) {
        super(message);
    }

    public InvalidTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
