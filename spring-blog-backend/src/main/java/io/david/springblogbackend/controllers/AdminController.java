package io.david.springblogbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String getAdminPage() {
        return ("<button> <a href='/logout'> Logout </a> </button><hr>" +
                "<h1> Welcome to the Admin Page! </h1>");
    }

}
