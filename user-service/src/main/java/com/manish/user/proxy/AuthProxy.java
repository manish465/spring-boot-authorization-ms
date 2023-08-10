package com.manish.user.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AuthServiceMS/api/auth")
public interface AuthProxy {
    @GetMapping("/create")
    String generateToken(@RequestParam(value = "username") String username,
                                @RequestParam(value = "roles") String roles);
}
