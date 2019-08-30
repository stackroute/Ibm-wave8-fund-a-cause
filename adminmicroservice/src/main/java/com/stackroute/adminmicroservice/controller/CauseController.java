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
@RequestMapping("/api/v1/cause")
public class CauseController{

    private static final String TOPIC = "DeleteCause";
    private static final String TOPIC2 = "UpdateCause";



    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCause(@PathVariable String id){

        //send the id to the cause microService using kafka to delete cause
        kafkaTemplate.send(TOPIC,id);
        ResponseEntity responseEntity = new ResponseEntity("Deleted Successfully ", HttpStatus.OK);

        return  responseEntity;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCauseDetails(@RequestBody  Object cause) {

        //send the updated cause to the cause microService using kafka to update cause
        kafkaTemplate.send(TOPIC2,cause);
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<String>("Cause details updated!", HttpStatus.CREATED);

        return responseEntity;
    }

}
