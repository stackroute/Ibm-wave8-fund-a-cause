package com.stackroute.Receipient.repository;

import com.stackroute.Receipient.Domain.Receipient;
import com.stackroute.Receipient.Repository.ReceipientRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReceipientRepositoryTest {
    @Autowired
    ReceipientRepository receipientRepository;
    Receipient receipient;
    @Before
    public void setUp()
    {
        receipient = new Receipient();
        receipient.setId("nandu");
        receipient.setName("nandini");
        receipient.setEmail("n@gmail.com");
        receipient.setPassword("nnnnn");
        receipient.setPhoneNumber(123456);
        receipient.setRole("Receipient");
    }
    @After
    public void tearDown(){

        receipientRepository.deleteAll();
    }

    @Test
    public void testSaveReceipient() {
        receipientRepository.save(receipient);
        Receipient fetchReceipient = receipientRepository.findById(receipient.getId()).get();
        Assert.assertEquals("nandu", fetchReceipient.getId());


    }
    @Test
    public void testSaveReceipientFailure() {
        Receipient receipient1 = new Receipient("nandu","nandini","n@gmail.com","nnnnn",123456,"Receipient");

        receipientRepository.save(receipient1);
        Receipient fetchReceipient = receipientRepository.findById(receipient.getId()).get();
        Assert.assertNotSame(receipient1, receipient);
    }

    @Test
    public void testGetAllTrack() {
        Receipient receipient1 = new Receipient("nandu","nandini","n@gmail.com","nnnnn",123456,"Receipient");
        Receipient receipient2 = new Receipient("niyati", "niyati mishra", "niyati@gmail.com","niyati",99999999,"receipient");
        receipientRepository.save(receipient1);
        receipientRepository.save(receipient2);

        List<Receipient> list = receipientRepository.findAll();
        Assert.assertEquals("nandini", list.get(0).getName());

    }
    @Test
    public void testGetTrackByIdFailure(){

        Receipient receipient1=new Receipient( "nandu","nandini","n@gmail.com","nnnnn",123456,"Receipient");
        Receipient receipient2 = new Receipient("niyati", "niyati mishra", "niyati@gmail.com","niyati",99999999,"receipient");
        receipientRepository.save(receipient1);
        receipientRepository.save(receipient2);
        List<Receipient> list = receipientRepository.findAll();
        Assert.assertNotEquals("n",list.get(0).getName());

    }

    @Test
    public void testUpdateReceipient(){
        receipientRepository.save(receipient);
        receipient.setId("nandu");
        receipient.setName("nandini");
        receipient.setEmail("nandu@gmail.com");
        receipient.setPassword("nandini");
        receipient.setPhoneNumber(123456);
        receipient.setRole("Receipient");
        receipientRepository.save(receipient);
        String comment = (receipientRepository.findById("nandu").get()).getId();
        Assert.assertEquals("nandu", comment);
    }
    @Test
    public void testUpdateReceipientFailure(){
        receipientRepository.save(receipient);
        receipient.setId("nandu");
        receipient.setName("nandini");
        receipient.setEmail("nandu@gmail.com");
        receipient.setPassword("nandini");
        receipient.setPhoneNumber(123456);
        receipient.setRole("Receipient");
        String comment = (receipientRepository.findById("nandu").get()).getId();
        Assert.assertNotEquals("nandi", comment);
    }
    @Test
    public void testDeleteReceipientFailure(){
        receipientRepository.save(receipient);
        Receipient fetchReceipient = receipientRepository.findById(receipient.getId()).get();
        Assert.assertNotEquals(null,fetchReceipient.getId());
    }

}
