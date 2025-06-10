package com.shoppingcart.root.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

public class AuthContext {
    public static String getAuthUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())){
            return auth.getName();
        }
        return null;
    }

    public static String getJwt(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

}
