package com.stackroute.CauseCRUD.Controller;


import com.stackroute.CauseCRUD.Domain.CauseCRUD;
import com.stackroute.CauseCRUD.Service.CauseCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="api/v1")
public class CauseCRUDController {
    private CauseCRUDService service;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public static final String TOPIC="Cause";

    @Autowired
    public CauseCRUDController(CauseCRUDService service) {
        this.service = service;
    }

    @PostMapping("cause")
    public ResponseEntity<?> saveNewCause(@RequestBody CauseCRUD cause){
        ResponseEntity responseEntity;
        /* try {
            byte[] ba = new ObjectMapper().writeValueAsString(owner).getBytes();
            kafkaTemplate.send(new ProducerRecord<byte[],byte[]>(TOPIC, ba));
            responseEntity = new ResponseEntity("Cause Successfully Created", HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        try{
            service.saveNewCause(cause);
            responseEntity=new ResponseEntity<String>("Cause is registered", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        kafkaTemplate.send(TOPIC,cause);
        return responseEntity;
    }

    /*Get all the causes*/
    @GetMapping("causes")
    public ResponseEntity<?> getAllCauses() {
        ResponseEntity responseEntity;
        try {

            responseEntity = new ResponseEntity<List<CauseCRUD>>(service.getAllCauses(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    /*Delete the cause using id*/
    @DeleteMapping({"cause/{id}"})
    public ResponseEntity<?> deleteCause(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            service.deleteCause(id);
            responseEntity = new ResponseEntity<String>("Cause deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /*Update the cause using id*/
    @PutMapping("cause/{id}")
    public ResponseEntity<?> updateCause(@RequestBody CauseCRUD causeCRUD ){
        ResponseEntity responseEntity;
        try {
            service.updateCausedetails(causeCRUD);
            responseEntity= new ResponseEntity<String>("Cause Details updated", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /*Get cause details using name attribute*/
    @GetMapping("cause/{name}")
    public ResponseEntity<?> getCauseByName(@PathVariable String name) {
        ResponseEntity responseEntity;
        try {
            List<CauseCRUD> causeList=service.getCauseByName(name);
            responseEntity = new ResponseEntity<List<CauseCRUD>>(causeList, HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

}
