package io.david.springblogbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.david.springblogbackend.models.User;
import io.david.springblogbackend.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find user"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateOrCreateUser(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setActive(newUser.isActive());

                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);

                    return userRepository.save(newUser);
                });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
