package io.david.springblogbackend.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.david.springblogbackend.services.MyUserDetailService;
import io.david.springblogbackend.utils.JwtUtils;

//Filter that will examine each request once, for a valid token
//If valid token is found, user info is saved in security context
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private MyUserDetailService userDetailService;
    private JwtUtils jwtUtils;

    public JwtRequestFilter(MyUserDetailService userDetailService, JwtUtils jwtUtils) {
        this.userDetailService = userDetailService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // If there is a header (in the request) named Authrization, it will save its
        // content to the var
        // Should contain "Bearer 'jwttoken'"
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Substring gets just the jwt
            jwt = authorizationHeader.substring(7);
            // Using the utils, the username is extracted
            username = jwtUtils.extractUsername(jwt);
        }

        // Extracting User Details using the username
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

            // If user details are extracted, next step is to validate
            if (jwtUtils.validateToken(jwt, userDetails)) {
                // Creates a new username/passauthentication object using the information in the
                // request body
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}
