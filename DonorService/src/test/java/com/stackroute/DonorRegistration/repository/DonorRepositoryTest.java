package com.stackroute.DonorRegistration.repository;

import com.stackroute.donorregistration.domain.Donor;
import com.stackroute.donorregistration.repository.DonorRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@DataMongoTest
public class DonorRepositoryTest {

    @Autowired
    DonorRepository donorRepository;
    Donor donor;

    @Before
    public void setUp() {
        donor = new Donor();
        donor.setId("ash123");
        donor.setName("Ashmita");
        donor.setEmail("ash123@gmail.com");
        donor.setPassword("123456");
        donor.setPhoneNumber(12321444);
        donor.setRole("Donor");
        donor.setOperation(1);
    }

    @After
    public void tearDown() {

        donorRepository.deleteAll();
    }


    @Test
    public void testSaveDonor() {
        donorRepository.save(donor);
        Donor fetchDonor = donorRepository.findById(donor.getId()).get();
        Assert.assertEquals("ash123", fetchDonor.getId());

    }

    @Test
    public void testSaveDonorFailure() {
        Donor testDonor = new Donor("ash123","Ashmita","ash123@gmail.com","123456","Donor",213123213,1);
        donorRepository.save(donor);
        Donor fetchDonor = donorRepository.findById(donor.getId()).get();
        Assert.assertNotSame(testDonor, donor);
    }

    @Test
    public void testGetAllDonors() {

        Donor testDonor = new Donor("ash123","Ashmita","ash123@gmail.com","123456","Donor",787987987,1);
        Donor testDonor1 = new Donor("sab123","Sabyasachi","sabya123@gmail.com","1234567","Donor",342142443,1);
        donorRepository.save(testDonor);
        donorRepository.save(testDonor1);

        List<Donor> list = donorRepository.findAll();
        Assert.assertEquals("Ashmita", list.get(0).getName());


    }

    @Test
    public void testGetDonorByIdFailure() {

        Donor testDonor = new Donor("ash123","Ashmita","ash123@gmail.com","123456","Donor",341515132,1);
        Donor testDonor1 = new Donor("sab123","Sabyasachi","sabya123@gmail.com","1234567","Donor",312434324,1);
        donorRepository.save(testDonor);
        donorRepository.save(testDonor1);
        List<Donor> list = donorRepository.findAll();
        Assert.assertNotEquals("Bad", list.get(0).getName());

    }

}
