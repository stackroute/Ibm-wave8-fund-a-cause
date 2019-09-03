package com.stackroute.donationservice.controller;
import com.stackroute.donationservice.domain.Donation;
import com.stackroute.donationservice.exception.DonationNotFoundException;
import com.stackroute.donationservice.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DonationController {

    private DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {

        this.donationService = donationService;
    }

    /*register the donation*/
    @PostMapping("donation")
    public ResponseEntity<?> saveNewDonation(@RequestBody Donation donation) {
        ResponseEntity responseEntity = null;
        donationService.saveNewDonation(donation);
        responseEntity = new ResponseEntity<String>("Donated ", HttpStatus.CREATED);

            return responseEntity;
    }

    /*Get all the Registered Donations*/
    @GetMapping("donations")
    public ResponseEntity<?> getAllDonation() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Donation>>(donationService.getAllDonation(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    //Get all the registered donations by DonationId
    @GetMapping("/donationId/{donationId}")
    public ResponseEntity<Optional<Donation>> searchByDonationId(@PathVariable String donationId) throws  DonationNotFoundException{
        ResponseEntity responseEntity;
        try {
            Optional<Donation> donation = donationService.getByDonationId(donationId);
            return new ResponseEntity<Optional<Donation>>(donation, HttpStatus.OK);
        } catch (DonationNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    //Get all the registered donations by ReceiverId
    @GetMapping("/Rdonation/{receiverId}")
    public ResponseEntity<Optional<Donation>> searchByReceiverId(@PathVariable String receiverId) throws DonationNotFoundException {
        ResponseEntity responseEntity;
        try {
            Optional<Donation> donation = donationService.getByReceiverId(receiverId);
            return new ResponseEntity<Optional<Donation>>(donation, HttpStatus.OK);
        } catch (DonationNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }

    //Get all the registered donations by DonorId
    @GetMapping("/Ddonation/{donorId}")
    public ResponseEntity<Optional<Donation>> searchByDonorId(@PathVariable String donorId) throws DonationNotFoundException{
        ResponseEntity responseEntity;
        try {
            Optional<Donation> donation = donationService.getByDonorId(donorId);
            return new ResponseEntity<Optional<Donation>>(donation, HttpStatus.OK);
        } catch (DonationNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }


    //Get all the registered donations by DonationId
    @GetMapping("/Cdonation/{causeId}")
    public ResponseEntity<Optional<Donation>> searchByCauseId(@PathVariable String causeId) throws DonationNotFoundException {

        ResponseEntity responseEntity;
        try {
            Optional<Donation> donation = donationService.getByCauseId(causeId);
            return new ResponseEntity<Optional<Donation>>(donation, HttpStatus.OK);
        } catch (DonationNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }
}


