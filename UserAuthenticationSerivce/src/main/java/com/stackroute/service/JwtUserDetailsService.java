package com.stackroute.service;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stackroute.dao.UserDao;

import com.stackroute.model.UserDTO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


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
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public Users save(Users user) {
        Users newUser = new Users();
      newUser.setEmail(user.getEmail());
       newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
       newUser.setRole(user.getRole());
       return userDao.save(newUser);
   }
public String forgotPassword(String username) throws MessagingException {
    String status = "Failed";
    System.out.println(username);
    System.out.println(userDao.findByEmail(username));
    System.out.println("abcd");
    if (userDao.findByEmail(username) != null) {
        System.out.println(username);
        System.out.println("efgh");
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(username);
        helper.setSubject("Link for Reset your Password");
        helper.setText("http://172.23.238.212:4200/resetPassword");
        javaMailSender.send(message);
        System.out.println("hello");
        status = "Sent";
    }
    else {

    }
    return status;
}
    public Users update(UserDTO userDTO) throws Exception {
        Users user = userDao.findByEmail(userDTO.getEmail());
        if (user != null) {
            user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        }
        return userDao.save(user);
    }
}

