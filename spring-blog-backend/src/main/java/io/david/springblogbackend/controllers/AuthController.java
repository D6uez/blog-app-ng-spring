package io.david.springblogbackend.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.david.springblogbackend.models.AuthenticationRequest;
import io.david.springblogbackend.models.AuthenticationResponse;
import io.david.springblogbackend.models.RegisterRequest;
import io.david.springblogbackend.models.User;
import io.david.springblogbackend.repositories.UserRepository;
import io.david.springblogbackend.services.MyUserDetailService;
import io.david.springblogbackend.utils.JwtUtils;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private MyUserDetailService userDetailService;
    private JwtUtils jwtUtils;
    private UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, MyUserDetailService userDetailService,
            JwtUtils jwtUtils, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest)
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new String("Error: Username is already taken!"));
        }

        User newUser = new User(registerRequest.getUsername(), registerRequest.getPassword(),
                registerRequest.getFirstName(), registerRequest.getLastName());

        if (newUser.getRoles() == null) {
            newUser.setRoles("ROLE_USER");
        }

        userRepository.save(newUser);

        return ResponseEntity.ok(new String("User Created."));

    }
}
