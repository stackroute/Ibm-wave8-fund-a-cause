package com.stackroute.adminmicroservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/v1/cause")
public class CauseController{

    private static final String TOPIC = "admin";


    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @GetMapping
    @KafkaListener(topics = "Registration", groupId = "group_id")
    public Object getAllCauses(Object message) {

        logger.info(String.format("$$ -> Consumed Message -> %s", message));

        return message;

    }

    @GetMapping("/causes/{name}")
    @KafkaListener(topics = "Registration", groupId = "group_id")
    public List<Object> getByName(List<Object> causes )  {
        logger.info(String.format("$$ -> Consumed Message -> %s", causes));

        return causes;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCause(@PathVariable String id){

        kafkaTemplate.send(TOPIC,id);
        ResponseEntity responseEntity = new ResponseEntity("Deleted Successfully ", HttpStatus.OK);

        return  responseEntity;
    }


}
