package com.stackroute.donationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.donationservice.domain.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationService {
    public Donation saveNewDonation(Donation donation);
    public List<Donation> getAllDonation();
    public Optional<Donation> getById(String Id);


}

