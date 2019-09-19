package com.stackroute.donationservice.service;

import com.stackroute.donationservice.domain.Donation;
import com.stackroute.donationservice.exception.DonationNotFoundException;
import com.stackroute.donationservice.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    private DonationRepository donationRepository;

    public static final String TOPIC = "fa-registration_donation";

    @Autowired
    private KafkaTemplate<String, Donation> kafkaTemplate;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }


    @Override
    public Donation saveNewDonation(Donation donation) {
        Donation savedDonor = donationRepository.save(donation);
        kafkaTemplate.send(TOPIC,donation);
        return savedDonor;
    }

    @Override
    public List<Donation> getAllDonation() {
        List<Donation> donationList = donationRepository.findAll();
        return donationList;
    }

    @Override
    public Optional<Donation> getByDonationId(String id) throws DonationNotFoundException {
        Optional<Donation> donation = donationRepository.searchByDonationId(id);
        if (donation.isEmpty()) {
            throw new DonationNotFoundException("Donation for particular DonationId does not exists!");
        }
        return Optional.of(donation.get());
    }

    @Override
    public Optional<Donation> getByDonorId(String id) throws DonationNotFoundException {
        Optional<Donation> donation = donationRepository.searchByDonorId(id);
        if (donation.isEmpty()) {
            throw new DonationNotFoundException("Donation for particular DonorId does not exists!");
        }
        return Optional.of(donation.get());
    }



    @Override
    public Optional<Donation> getByReceiverId(String id) throws DonationNotFoundException {
        Optional<Donation> donation = donationRepository.searchByReceiverId(id);
        if (donation.isEmpty()) {
            throw new DonationNotFoundException("Donation for particular ReceiverId does not exists!");
        }
            return Optional.of(donation.get());
        }


    @Override
    public Optional<Donation> getByCauseId(String id) throws DonationNotFoundException {
        Optional<Donation> donation = donationRepository.searchByCauseId(id);
        if (donation.isEmpty()) {
            throw new DonationNotFoundException("Donation for particular CauseId does not exists!");
        }
        return Optional.of(donation.get());
    }
}

