package io.david.springblogbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.david.springblogbackend.models.AuthenticationRequest;
import io.david.springblogbackend.models.AuthenticationResponse;
import io.david.springblogbackend.services.MyUserDetailService;
import io.david.springblogbackend.utils.JwtUtils;

@Controller
public class AuthController {

    private AuthenticationManager authenticationManager;
    private MyUserDetailService userDetailService;
    private JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, MyUserDetailService userDetailService,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {

        try {
            // Attempts to authenticate username and password combination from MySQL db
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        // If authentication is successful, the user details (principal) are saved
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        // Token Generation using the user details via the jwt utility
        final String jwt = jwtUtils.generateToken(userDetails);

        // After token is generated, return a response with the generated token for
        // client to use with each future request
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
