package io.david.springblogbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return ("<button><a href='/user'>User Page</a></button> <button><a href='/admin'>Admin Page</a></button><hr>" +
                "<h1>Welcome to the Home Page! </h1>");
    }
}
