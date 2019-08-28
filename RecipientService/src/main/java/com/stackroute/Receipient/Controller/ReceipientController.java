package com.stackroute.Receipient.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Receipient.Domain.Receipient;
import com.stackroute.Receipient.Service.ReceipientService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="api/v1")
public class ReceipientController {
    private ReceipientService service;

   @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public static final String TOPIC="Registration";

    @Autowired
    public ReceipientController(ReceipientService service) {
        this.service = service;
    }

    /*Registration of new Owner*/

    @PostMapping("owner")
    public ResponseEntity<?> saveNewOwner(@RequestBody Receipient owner){
        ResponseEntity responseEntity;
       /* try {
            byte[] ba = new ObjectMapper().writeValueAsString(owner).getBytes();
            kafkaTemplate.send(new ProducerRecord<byte[],byte[]>(TOPIC, ba));
            responseEntity = new ResponseEntity("Owner Successfully Registered", HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
       try{
            service.saveNewProductOwner(owner);
            responseEntity=new ResponseEntity<String>("Owner is registered", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        kafkaTemplate.send(TOPIC,owner);

        return responseEntity;
    }


    /*Get all the Registered Owners*/
    @GetMapping("owners")
    public ResponseEntity<?> getAllOwners() {
        ResponseEntity responseEntity=null;
        try {

            responseEntity = new ResponseEntity<List<Receipient>>(service.getAllOwners(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }

        return responseEntity;
    }

    /*Delete the Registered Owners using id*/
    @DeleteMapping({"owner/{id}"})
    public ResponseEntity<?> deleteOwner(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            service.deleteOwner(id);
            responseEntity = new ResponseEntity<String>("Owner deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /*Update the Registered Owners using id*/
    @PutMapping("owner/{id}")
    public ResponseEntity<?> UpdateOwner(@RequestBody Receipient owner ){
        ResponseEntity responseEntity;
        try {
            service.updateOwnerdetails(owner);
            responseEntity= new ResponseEntity<String>("Owner Details updated", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    /*Get owner details using name attribute*/
    @GetMapping("owner/{name}")
    public ResponseEntity<?> getOwnerByName(@PathVariable String name) {
        ResponseEntity responseEntity;
        try {
            List<Receipient> receipient =service.getProductOwnerByName(name);
            responseEntity = new ResponseEntity<List<Receipient>>(receipient, HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

}
