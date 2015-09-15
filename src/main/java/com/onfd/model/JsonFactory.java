/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import com.onfd.security.UserContainer;

/**
 *
 * @author Aleksey Malyshev
 */
public class JsonFactory {

    private final UserContainer userContainer = new UserContainer();

    public <T> T getCurrentUser() {
        return userContainer.getCurrentUser();
    }

    public Measurement getMeasurement(int id) {
        Customer customer = getCurrentUser();
        return customer.getMeasurement(id);
    }

}
