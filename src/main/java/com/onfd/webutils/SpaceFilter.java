/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.webutils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aleksey Malyshev
 */
public class SpaceFilter implements Filter {

    private static final Logger _log;

    static {
        _log = Logger.getLogger(SpaceFilter.class.getName());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean doFilter(HttpServletRequest request) {
        try {
            URI uri = new URI(request.getRequestURL().toString());
        }
        catch (URISyntaxException e) {
            _log.log(Level.WARNING, "Invalid URL", e);
        }
        return true;
    }
}
