package com.example.SpringSecurityFreeDemo.service;

import com.example.SpringSecurityFreeDemo.model.UserPrincipal;
import com.example.SpringSecurityFreeDemo.model.Users;
import com.example.SpringSecurityFreeDemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByEmail(username);

        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }

        return user;
    }
}
