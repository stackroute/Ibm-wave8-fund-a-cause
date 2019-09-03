package com.stackroute.donationservice.service;


import com.stackroute.donationservice.domain.Donation;
import com.stackroute.donationservice.exception.DonationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DonationService {
    public Donation saveNewDonation(Donation donation);
    public List<Donation> getAllDonation();
    public Optional<Donation> getByDonationId(String Id) throws DonationNotFoundException;
    public Optional<Donation> getByDonorId(String Id)throws DonationNotFoundException;
    public Optional<Donation> getByReceiverId(String Id) throws DonationNotFoundException;
    public Optional<Donation> getByCauseId(String Id) throws DonationNotFoundException;


}

