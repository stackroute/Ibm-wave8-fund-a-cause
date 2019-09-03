package com.stackroute.donationservice.repository;

import com.stackroute.donationservice.domain.Donation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class DonationRepositoryTest {

    @Autowired
    DonationRepository donationRepository;
    Donation donation;

    @Before
    public void setUp() {
        donation = new Donation();
        donation = new Donation();
        donation.setDonationId("2");
        donation.setReceiverId("22");
        donation.setDonorId("222");
        donation.setCauseId("2222");
        donation.setAmount(22222L);

    }

    @After
    public void tearDown() {

        donationRepository.deleteAll();
    }


    @Test
    public void testSaveDonor() {
        donationRepository.save(donation);
        Donation fetchDonation = donationRepository.findById(donation.getDonationId()).get();
        Assert.assertEquals("2", fetchDonation.getDonationId());

    }
    @Test
    public void testGetAllDonation() {

        Donation testDonation1 = new Donation("2","22","222","2222",22222L);
        Donation testDonation2 = new Donation("4","44","444","4444",44444L);
        donationRepository.save(testDonation1);
        donationRepository.save(testDonation2);

        List<Donation> list = donationRepository.findAll();
        Assert.assertEquals("2", list.get(0).getDonationId());


    }

}