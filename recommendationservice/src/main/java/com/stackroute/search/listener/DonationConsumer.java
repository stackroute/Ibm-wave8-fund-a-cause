package com.stackroute.search.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.search.models.Donation;
import com.stackroute.search.repository.DonationHistoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;

@Service
public class DonationConsumer {

    private DonationHistoryRepository donationHistoryRepository;

    public DonationConsumer(DonationHistoryRepository donationHistoryRepository) {
        this.donationHistoryRepository = donationHistoryRepository;
    }

    @KafkaListener(topics="fa-registration_donation",groupId = "group_id_donation")
    public void consume(Donation donation) throws IOException {

        System.out.println(donation);

        System.out.println(donationHistoryRepository.save(donation));

    }

}
