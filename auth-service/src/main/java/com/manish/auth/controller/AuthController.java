package com.manish.auth.controller;

import com.manish.auth.dto.ClaimsDataDTO;
import com.manish.auth.security.JwtHelper;
import io.jsonwebtoken.Claims;
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

    @GetMapping("/validate")
    public ClaimsDataDTO validateToken(@RequestParam(value = "token") String token){
        if(!jwtHelper.validateToken(token)) throw new RuntimeException("Invalid access");

        String username = jwtHelper.getUsernameFromToken(token);
        Claims claims = jwtHelper.getAllClaimsFromToken(token);

        return new ClaimsDataDTO(username, (String) claims.get("roles"));
    }
}
