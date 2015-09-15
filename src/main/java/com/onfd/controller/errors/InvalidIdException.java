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
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid object id.")
public class InvalidIdException extends RuntimeException {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
