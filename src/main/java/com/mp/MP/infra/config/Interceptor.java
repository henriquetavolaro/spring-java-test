package com.mp.MP.infra.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Interceptor extends OncePerRequestFilter {

    @Value("${token}")
    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            var authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null) {
                throw new RuntimeException("No token provided");
            }
            if (!authorizationHeader.replace("Bearer ", "").equals(token)) {
                throw new RuntimeException("Token invalid");
            }
            filterChain.doFilter(request, response);
        } catch (RuntimeException ex) {
            response.setStatus(401);
            response.getWriter().write(ex.getMessage());
        }
    }
}