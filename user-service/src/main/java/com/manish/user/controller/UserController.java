package com.manish.user.controller;

import com.manish.user.dto.LoginDTO;
import com.manish.user.entity.UserEntity;
import com.manish.user.proxy.AuthProxy;
import com.manish.user.repository.UserRepository;
import com.manish.user.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthProxy authProxy;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity user){
        Optional<UserEntity> presentUser = userRepository.findByUsername(user.getUsername());

        if(presentUser.isPresent()) throw new RuntimeException("User already exist!!");

        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        if(!authentication.isAuthenticated()) throw new RuntimeException("Invalid credentials");

        return authProxy.generateToken(authentication.getName(), Convertor.extractAuthoritiesToString(authentication));
    }
}
