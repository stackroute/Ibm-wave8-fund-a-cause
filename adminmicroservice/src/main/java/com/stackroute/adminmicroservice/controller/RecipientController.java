package com.stackroute.adminmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.function.Consumer;

@RestController
@RequestMapping("/api/v1/recipient")
public class RecipientController{

    private static final String TOPIC = "DeleteRecipient";
    private static final String TOPIC2 = "RecipientResetPassword";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecipient(@PathVariable String id){

        //send the id to the recipient microService using kafka to delete recipient

        kafkaTemplate.send(TOPIC,id);
        ResponseEntity responseEntity = new ResponseEntity("Deleted Successfully ", HttpStatus.OK);

        return  responseEntity;
    }


    /*Updation of recipient details*/
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRecipientDetails(@RequestBody Object recipient) {

        //send the updated recipient to the recipient microService using kafka to update recipient

        kafkaTemplate.send(TOPIC2,recipient);

        ResponseEntity responseEntity;
            responseEntity= new ResponseEntity<String>("Donor details updated!", HttpStatus.CREATED);

        return responseEntity;
    }


}
