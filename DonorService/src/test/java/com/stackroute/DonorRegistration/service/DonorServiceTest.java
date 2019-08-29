package com.stackroute.DonorRegistration.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.DonorRegistration.Domain.Donor;
import com.stackroute.DonorRegistration.Exceptions.DonorAlreadyExistsException;
import com.stackroute.DonorRegistration.Exceptions.DonorNotFoundException;
import com.stackroute.DonorRegistration.Repository.DonorRepository;
import com.stackroute.DonorRegistration.Service.DonorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class DonorServiceTest {

    Donor donor;

    //Create a mock for UserRepository
    @Mock
    DonorRepository donorRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    DonorServiceImpl donorService;
    List<Donor> list = null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        donor = new Donor();
        donor.setId("ash123");
        donor.setName("Ashmita");
        donor.setEmail("ash123@gmail.com");
        donor.setPassword("123456");
        donor.setPhoneNumber(12321444);
        donor.setRole("Donor");
        list = new ArrayList<>();
        list.add(donor);


    }

    @Test
    public void saveNewDonorTestSuccess() throws DonorAlreadyExistsException, JsonProcessingException {

        when(donorRepository.save((Donor) any())).thenReturn(donor);
        Donor savedDonor = donorService.saveNewDonor(donor);
        assertEquals(donor,savedDonor);

        //verify here verifies that userRepository save method is only called once
        verify(donorRepository,times(1)).save(donor);

    }

    @Test
    public void saveDonorTestFailure() throws DonorAlreadyExistsException, JsonProcessingException {
        when(donorRepository.save((Donor) any())).thenReturn(null);
        Donor savedDonor = donorService.saveNewDonor(donor);

    }

    @Test
    public void testGetAllDonors() throws DonorNotFoundException {

        donorRepository.save(donor);
        //stubbing the mock to return specific data
        when(donorRepository.findAll()).thenReturn(list);
        List<Donor> userlist = donorService.getAllDonors();
        assertEquals(list,userlist);
    }

    @Test
    public void deleteDonorTestSuccess() throws DonorAlreadyExistsException {

        donorRepository.delete(donor);
        boolean deletedDonor = donorRepository.existsById("ash123");
        assertEquals(false,deletedDonor);
    }

    @Test
    public void updateDonorTest() throws DonorNotFoundException
    {
        when(donorRepository.save((Donor) any())).thenReturn(donor);
        Donor updateDonor = null;
        try {
            updateDonor = donorService.saveNewDonor(donor);
        } catch (DonorAlreadyExistsException | JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(donor,updateDonor);
    }


}