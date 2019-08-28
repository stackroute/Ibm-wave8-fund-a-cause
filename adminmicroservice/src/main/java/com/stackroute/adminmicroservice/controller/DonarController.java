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
@RequestMapping("/api/v1/donar")
public class DonarController {

    private static final String TOPIC = "DeleteDonor";
    private static final String TOPIC2 = "DonorPasswordReset";

    @Autowired
    private KafkaTemplate<String , Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @GetMapping
    @KafkaListener(topics = "Registration", groupId = "group_id")
    public List<Object> getAllDonars(List<Object> message) {

        logger.info(String.format("$$ -> Consumed Message -> %s",message));

        return message;



    }

    @GetMapping("/donars/{name}")
    @KafkaListener(topics = "Registration", groupId = "group_id")
    public List<Object> getByName(List<Object> donars )  {
        logger.info(String.format("$$ -> Consumed Message -> %s", donars));

        return donars;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDonar(@PathVariable String id){

        kafkaTemplate.send(TOPIC,id);
        ResponseEntity responseEntity = new ResponseEntity("Deleted Successfully ", HttpStatus.OK);

        return  responseEntity;
        }

   @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDonorDetails(@RequestBody  Object donor) {

        kafkaTemplate.send(TOPIC2,donor);
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<String>("Donor details updated!", HttpStatus.CREATED);

        return responseEntity;
    }

}
