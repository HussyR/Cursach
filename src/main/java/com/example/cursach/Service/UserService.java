package com.example.cursach.Service;

import com.example.cursach.DTO.UserRegistrationDTO;
import com.example.cursach.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {
    User save(UserRegistrationDTO registrationDto);
    User loadUserByEmail(String email);
}
