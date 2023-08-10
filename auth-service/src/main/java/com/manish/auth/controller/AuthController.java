package com.manish.auth.controller;

import com.manish.auth.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtHelper jwtHelper;

    @GetMapping("/create")
    public String generateToken(@RequestParam(value = "username") String username,
                                @RequestParam(value = "roles") String roles){
        return jwtHelper.generateToken(username, roles);
    }
}
