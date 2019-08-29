package com.stackroute.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.dao.UserDao;
import com.stackroute.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @KafkaListener(topics = "registration", groupId="group_id")
   public Users consumeJson(Users user) throws IOException {
//        User user = new ObjectMapper().readValue(message, Users.class);
        System.out.println("Consumed JSON Message: " + user);
        Users newUser = new Users();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        return userDao.save(newUser);

    }
}
