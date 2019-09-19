package com.stackroute.Cause.repository;


import com.stackroute.cause.domain.Cause;
import com.stackroute.cause.repository.CauseRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CauseRepositoryTest {
    @Autowired
    CauseRepository causeRepository;
    Cause cause;
    @Before
    public void setUp()
    {
        cause = new Cause();
        cause.setId("f123");
        cause.setName("relief");
        cause.setReceiverId("nan123");
        cause.setCauseType("Floods");
        cause.setCauseDescription("aa");
        cause.setTimestamp("21-01-2019");
        cause.setLocation("Chennai");
        cause.setAmount(12345678);
    }
    @After
    public void tearDown(){

        causeRepository.deleteAll();
    }

    @Test
    public void testSaveCause() {
        causeRepository.save(cause);
        Cause fetchCause = causeRepository.findById(cause.getId()).get();
        Assert.assertEquals("f123", fetchCause.getId());

    }
    @Test
    public void testSaveCauseFailure() {
        Cause cause1 = new Cause("f123","relief","nan123","Floods","aa","21-01-2019" ,"Chennai",12345678);

        causeRepository.save(cause1);
        Cause fetchCause = causeRepository.findById(cause.getId()).get();
        Assert.assertNotSame(cause1, cause);
    }

    @Test
    public void testGetAllCauses() {
        Cause cause1 = new Cause("f123","relief","nan123","Floods","aa","21-01-2019" ,"Chennai",12345678);
        causeRepository.save(cause1);


        List<Cause> list = causeRepository.findAll();
        Assert.assertEquals("relief", list.get(0).getName());

    }
    @Test
    public void testGetCauseByIdFailure(){

        Cause cause1 = new Cause("f123","relief","nan123","Floods","aa","21-01-2019" ,"Chennai",12345678);

        causeRepository.save(cause1);
        List<Cause> list = causeRepository.findAll();
        Assert.assertNotEquals("n",list.get(0).getName());

    }

    @Test
    public void testUpdateReceipient(){

        cause.setId("f123");
        cause.setName("relief");
        cause.setReceiverId("nan123");
        cause.setCauseType("Floods");
        cause.setCauseDescription("aa");
        cause.setTimestamp("21-01-2019");
        cause.setLocation("Chennai");
        cause.setAmount(12345678);
        causeRepository.save(cause);
        String comment = (causeRepository.findById("f123").get()).getId();
        Assert.assertEquals("f123", comment);
    }
    @Test
    public void testUpdateReceipientFailure(){
        cause.setId("f123");
        cause.setName("relief");
        cause.setReceiverId("nan123");
        cause.setCauseType("Floods");
        cause.setCauseDescription("aa");
        cause.setTimestamp("21-01-2019");
        cause.setLocation("Chennai");
        cause.setAmount(12345678);
        causeRepository.save(cause);
        String comment = (causeRepository.findById("f123").get()).getId();
        Assert.assertNotEquals("n", comment);
    }
    @Test
    public void testDeleteCauseFailure(){
        causeRepository.save(cause);
        Cause fetchCause = causeRepository.findById(cause.getId()).get();
        Assert.assertNotEquals(null,fetchCause.getId());
    }

}
