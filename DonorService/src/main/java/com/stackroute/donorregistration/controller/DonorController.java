package com.stackroute.donorregistration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.donorregistration.domain.Donor;
import com.stackroute.donorregistration.exceptions.DonorAlreadyExistsException;
import com.stackroute.donorregistration.exceptions.DonorNotFoundException;
import com.stackroute.donorregistration.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class DonorController  {

    private DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {

        this.donorService = donorService;
    }

    /*registration of new Donor*/
    @PostMapping("donor")
    public ResponseEntity<?> saveNewDonor(@RequestBody Donor donor) {
        ResponseEntity responseEntity = null;
        try{
            donorService.saveNewDonor(donor);
            responseEntity= new ResponseEntity<String>("Donor is registered", HttpStatus.CREATED);
        } catch (DonorAlreadyExistsException | JsonProcessingException e) {
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    /*Get all the Registered Donors*/
    @GetMapping("donors")
    public ResponseEntity<?> getAllDonors() {
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<List<Donor>>(donorService.getAllDonors(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    /* Deletion of  a Donor by id*/
    @DeleteMapping("/donor/{id}")
    public ResponseEntity<Donor> removeDonor(@PathVariable String id) {

        try {
            donorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (DonorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }

/*Updation of donor details*/
    @PutMapping("/donor/{id}")
    public ResponseEntity<Donor> updateDonorDetails(@RequestBody Donor donor) {

        ResponseEntity responseEntity;
        try{
            donorService.updateById(donor);
            responseEntity= new ResponseEntity<String>("Donor details updated!", HttpStatus.CREATED);
        } catch (DonorNotFoundException e) {
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/donors/{name}")
    public ResponseEntity<List<Donor>> getByName(@PathVariable String name) throws DonorNotFoundException {
        List<Donor> donor = donorService.getByName(name);
        return new ResponseEntity<List<Donor>>(donor, HttpStatus.OK);
    }

}
