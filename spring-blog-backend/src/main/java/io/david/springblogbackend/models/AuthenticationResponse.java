package io.david.springblogbackend.models;

public class AuthenticationResponse {

    private final String jwt;
    private final String username;
    private final String roles;

    public AuthenticationResponse(String jwt, String username, String roles) {
        this.jwt = jwt;
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }

    public String getJwt() {
        return jwt;
    }

}
