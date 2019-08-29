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
@RequestMapping("/api/v1/donor")
public class DonarController {

    private static final String TOPIC = "DeleteDonor";
    private static final String TOPIC2 = "DonorPasswordReset";

    @Autowired
    private KafkaTemplate<String , Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable String id){

        //send the id to the donor microService using kafka to delete donor

        kafkaTemplate.send(TOPIC,id);
        ResponseEntity responseEntity = new ResponseEntity("Deleted Successfully ", HttpStatus.OK);

        return  responseEntity;
        }

   @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDonorDetails(@RequestBody Object donor) {

       //send the updated donor to the donor microService using kafka to update donor

       kafkaTemplate.send(TOPIC2,donor);
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<String>("Donor details updated!", HttpStatus.CREATED);

        return responseEntity;
    }

}
