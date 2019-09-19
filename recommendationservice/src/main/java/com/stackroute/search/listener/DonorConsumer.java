package com.stackroute.search.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.search.models.Cause;
import com.stackroute.search.models.Donor;
import com.stackroute.search.repository.DonationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;

@Service
public class DonorConsumer {

    private DonationRepository donationRepository;

    public DonorConsumer(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @KafkaListener(topics="fa-registration",groupId = "group_id_donor")
    public void consume(Donor donor) throws IOException {

        System.out.println(donor);

        System.out.println(donationRepository.save(donor));

    }

}
