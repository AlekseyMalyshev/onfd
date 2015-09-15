/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.security;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
 *
 * @author Aleksey Malyshev
 */
public class OnfdAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Map<String, String> roleUrlMap;
    private String unauthorizedUrl;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl;
        if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();
            requestCache.removeRequest(request, response);
        }
        else {
            targetUrl = getDefaultUserUrl(authentication);
        }

        if (targetUrl != null) {
            clearAuthenticationAttributes(request);
        }
        else {
            targetUrl = unauthorizedUrl;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String getDefaultUserUrl(Authentication authentication) {
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String url = (String)roleUrlMap.get(auth.getAuthority());
            if (url != null) {
                return url;
            }
        }
        return null;
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

    /**
     * @return the roleUrlMap
     */
    public Map<String, String> getRoleUrlMap() {
        return roleUrlMap;
    }

    /**
     * @param roleUrlMap the roleUrlMap to set
     */
    public void setRoleUrlMap(Map<String, String> roleUrlMap) {
        this.roleUrlMap = roleUrlMap;
    }

    /**
     * @return the unauthorizedUrl
     */
    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    /**
     * @param unauthorizedUrl the unauthorizedUrl to set
     */
    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

}
