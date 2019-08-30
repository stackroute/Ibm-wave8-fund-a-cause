package com.stackroute.recipient.controller;

import com.stackroute.recipient.domain.Recipient;
import com.stackroute.recipient.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="api/v1")
public class RecipientController {
    private RecipientService service;

    @Autowired
    public RecipientController(RecipientService service) {
        this.service = service;
    }

    /*Registration of new Receipient*/

    @PostMapping("owner")
    public ResponseEntity<?> saveNewOwner(@RequestBody Recipient owner){
        ResponseEntity responseEntity;

       try{
            service.saveNewProductOwner(owner);
            responseEntity=new ResponseEntity<String>("Owner is registered", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }

        return responseEntity;
    }


    /*Get all the Registered Recipients*/
    @GetMapping("owners")
    public ResponseEntity<?> getAllOwners() {
        ResponseEntity responseEntity=null;
        try {

            responseEntity = new ResponseEntity<List<Recipient>>(service.getAllOwners(), HttpStatus.OK);
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
    public ResponseEntity<?> UpdateOwner(@RequestBody Recipient owner ){
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
    @GetMapping("owner/{id}")
    public ResponseEntity<?> getOwnerByName(@PathVariable String id) {
        ResponseEntity responseEntity;
        try {
            List<Recipient> recipient =service.getProductOwnerByName(id);
            responseEntity = new ResponseEntity<List<Recipient>>(recipient, HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

}