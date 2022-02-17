package io.david.springblogbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.david.springblogbackend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
