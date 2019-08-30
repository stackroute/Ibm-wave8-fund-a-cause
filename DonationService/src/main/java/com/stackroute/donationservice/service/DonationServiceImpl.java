package com.stackroute.donationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.donationservice.domain.Donation;
import com.stackroute.donationservice.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    private DonationRepository donationRepository;

    public static final String TOPIC = "registration";

    @Autowired
    private KafkaTemplate<String, Donation> kafkaTemplate;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }


    @Override
    public Donation saveNewDonation(Donation donation) {
        Donation savedDonor = donationRepository.save(donation);
        return savedDonor;
          }

    @Override
    public List<Donation> getAllDonation() {
        List<Donation> donationList = donationRepository.findAll();
        return donationList;
    }

    @Override
    public Optional<Donation> getById(String id) {
        Optional<Donation> donation = donationRepository.findById(id);
        return donation;
    }
}
