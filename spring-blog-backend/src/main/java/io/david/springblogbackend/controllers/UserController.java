package io.david.springblogbackend.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.david.springblogbackend.models.User;
import io.david.springblogbackend.services.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = { "", "/" })
    List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = { "", "/" })
    User newUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateOrCreateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
