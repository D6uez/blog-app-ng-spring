package io.david.springblogbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.david.springblogbackend.models.MyUserDetails;
import io.david.springblogbackend.models.User;
import io.david.springblogbackend.repositories.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    // Returns an instance of the UserDetails, that is found(maybe) in the database
    // by the username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Username not found in database:" + username));

        return user.map(MyUserDetails::new).get();
    }

}
