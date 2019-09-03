package com.stackroute.donationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.donationservice.domain.Donation;
import com.stackroute.donationservice.exception.DonationNotFoundException;
import com.stackroute.donationservice.repository.DonationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DonationServiceTest {

    Donation donation;

    //Create a mock for UserRepository
    @Mock
    DonationRepository donationRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    DonationServiceImpl donationService;
    List<Donation> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        donation = new Donation();
        donation = new Donation();
        donation.setDonationId("2");
        donation.setReceiverId("22");
        donation.setDonorId("222");
        donation.setCauseId("2222");
        donation.setAmount(22222L);
        list = new ArrayList<>();
        list.add(donation);


    }

    @Test
    public void saveNewDonorTestSuccess() throws JsonProcessingException {

        when(donationRepository.save((Donation) any())).thenReturn(donation);
        Donation savedDonation = donationService.saveNewDonation(donation);
        assertEquals(donation, savedDonation);

        //verify here verifies that userRepository save method is only called once
        //verify(donorRepository,times(1)).save(donor);

    }

    @Test
    public void testGetAllDonors() throws DonationNotFoundException {

        donationRepository.save(donation);
        //stubbing the mock to return specific data
        when(donationRepository.findAll()).thenReturn(list);
        List<Donation> userlist = donationService.getAllDonation();
        assertEquals(list, userlist);
    }


}