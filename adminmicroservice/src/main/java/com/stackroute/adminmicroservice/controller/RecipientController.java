package com.stackroute.adminmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/v1/recipient")
public class RecipientController{

    private static final String TOPIC = "DeleteRecipient";
    private static final String TOPIC2 = "RecipientResetPassword";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @GetMapping
    @KafkaListener(topics = "Registration", groupId = "group_id")
    public List<Object> getAllRecipients(List<Object> message) {

        logger.info(String.format("$$ -> Consumed Message -> %s", message));

        return message;

    }
    @GetMapping("/recipients/{name}")
    @KafkaListener(topics = "Registration", groupId = "group_id")
    public List<Object> getByName(List<Object> recipients )  {
        logger.info(String.format("$$ -> Consumed Message -> %s", recipients));

        return recipients;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecipient(@PathVariable String id){

        kafkaTemplate.send(TOPIC,id);
        ResponseEntity responseEntity = new ResponseEntity("Deleted Successfully ", HttpStatus.OK);

        return  responseEntity;
    }


    /*Updation of donor details*/
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDonorDetails(@RequestBody Object recipient) {

        kafkaTemplate.send(TOPIC2,recipient);

        ResponseEntity responseEntity;
            responseEntity= new ResponseEntity<String>("Donor details updated!", HttpStatus.CREATED);

        return responseEntity;
    }


}
