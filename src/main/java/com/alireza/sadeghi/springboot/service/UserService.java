package com.alireza.sadeghi.springboot.service;

import com.alireza.sadeghi.springboot.domain.Users;
import com.alireza.sadeghi.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService
//        implements UserDetailsService
{

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            Users user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//            Users user = userRepository.findByUsername(username);
//            if (user != null) return user;
//            throw new UsernameNotFoundException("User '" + username + "' not found");
//    }
}
