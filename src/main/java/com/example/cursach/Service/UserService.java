package com.example.cursach.Service;

import com.example.cursach.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(User user);
}
