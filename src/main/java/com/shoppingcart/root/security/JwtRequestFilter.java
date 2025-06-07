package com.shoppingcart.root.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
        }
        if (jwt != null) {
            try {
                Optional<String> username = jwtUtil.extractUsernameOpt(jwt);
                if (username.isPresent()) {
                    List<GrantedAuthority> authorities = jwtUtil.validateToken(jwt);
                    if (!authorities.isEmpty()) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(username.get(), null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception err) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("""
                {
                  "error": "Unauthorized",
                  "message": "%s",
                  "timestamp": "%s",
                  "status": 401,
                }
                """.formatted(err.getMessage(),java.time.Instant.now()));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}