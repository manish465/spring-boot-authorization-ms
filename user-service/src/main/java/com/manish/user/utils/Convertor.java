package com.manish.user.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class Convertor {
    public static String extractAuthoritiesToString(Authentication authentication){
        StringBuilder roles = new StringBuilder();

        for(GrantedAuthority authority: authentication.getAuthorities()){
            String authorityString = authority.toString();

            if(roles.length() > 0){
                roles.append(",");
            }
            roles.append(authorityString);
        }

        return roles.toString();
    }
}
