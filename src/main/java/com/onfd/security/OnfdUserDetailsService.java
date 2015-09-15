/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.security;

import com.onfd.model.SessionManager;
import com.onfd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksey Malyshev
 */
@Service
public class OnfdUserDetailsService implements UserDetailsService {

    @Autowired
    private SessionManager sessionManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = sessionManager.getOne(User.getQueryByName(), username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new OnfdUserDetails(user);
    }
}
