package com.stackroute.service;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stackroute.dao.UserDao;

import com.stackroute.model.UserDTO;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private UserDTO userDTO;

    public JwtUserDetailsService() {
    }

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

//    public Users save(Users user) {
//        Users newUser = new Users();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//        newUser.setRole(user.getRole());
//        return userDao.save(newUser);
//    }

}
