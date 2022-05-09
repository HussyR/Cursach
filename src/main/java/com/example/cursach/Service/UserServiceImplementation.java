package com.example.cursach.Service;

import com.example.cursach.Models.Role;
import com.example.cursach.Models.User;
import com.example.cursach.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        var user2 = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRole().getAuthorities());
        System.out.println(user2.getUsername());
        System.out.println(user2.getPassword());
        System.out.println(user2.getAuthorities());
        return user2;
    }
}
