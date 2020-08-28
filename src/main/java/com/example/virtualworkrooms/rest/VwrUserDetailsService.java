package com.example.virtualworkrooms.rest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VwrUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        //query a la database
        return new VwrUserDetails(nombre);
    }
    
}