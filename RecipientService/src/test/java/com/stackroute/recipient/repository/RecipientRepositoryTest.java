package com.stackroute.recipient.repository;

import com.stackroute.recipient.domain.Recipient;
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
public class RecipientRepositoryTest {
    @Autowired
    RecipientRepository recipientRepository;
    Recipient recipient;
    @Before
    public void setUp()
    {
        recipient = new Recipient();
        recipient.setId("nandu");
        recipient.setName("nandini");
        recipient.setEmail("n@gmail.com");
        recipient.setPassword("nnnnn");
        recipient.setPhoneNumber(123456);
        recipient.setRole("Receipient");
        recipient.setOperation(1);
    }
    @After
    public void tearDown(){

        recipientRepository.deleteAll();
    }

    @Test
    public void testSaveReceipient() {
        recipientRepository.save(recipient);
        Recipient fetchRecipient = recipientRepository.findById(recipient.getId()).get();
        Assert.assertEquals("nandu", fetchRecipient.getId());


    }
    @Test
    public void testSaveReceipientFailure() {
        Recipient recipient1 = new Recipient("nandu","nandini","n@gmail.com","nnnnn",79877987,"Recipient",1);

        recipientRepository.save(recipient1);
        Recipient fetchRecipient = recipientRepository.findById(recipient.getId()).get();
        Assert.assertNotSame(recipient1, recipient);
    }

    @Test
    public void testGetAllTrack() {
        Recipient recipient1 = new Recipient("nandu","nandini","n@gmail.com","nnnnn",123456,"Receipient",1);
        Recipient recipient2 = new Recipient("niyati", "niyati mishra", "niyati@gmail.com","niyati",99999999,"receipient",1);
        recipientRepository.save(recipient1);
        recipientRepository.save(recipient2);

        List<Recipient> list = recipientRepository.findAll();
        Assert.assertEquals("nandini", list.get(0).getName());

    }
    @Test
    public void testGetTrackByIdFailure(){

        Recipient recipient1 =new Recipient( "nandu","nandini","n@gmail.com","nnnnn",123456,"Receipient",1);
        Recipient recipient2 = new Recipient("niyati", "niyati mishra", "niyati@gmail.com","niyati",99999999,"receipient",1);
        recipientRepository.save(recipient1);
        recipientRepository.save(recipient2);
        List<Recipient> list = recipientRepository.findAll();
        Assert.assertNotEquals("n",list.get(0).getName());

    }

    @Test
    public void testUpdateReceipient(){
        recipientRepository.save(recipient);
        recipient.setId("nandu");
        recipient.setName("nandini");
        recipient.setEmail("nandu@gmail.com");
        recipient.setPassword("nandini");
        recipient.setPhoneNumber(123456);
        recipient.setRole("Receipient");
        recipientRepository.save(recipient);
        String comment = (recipientRepository.findById("nandu").get()).getId();
        Assert.assertEquals("nandu", comment);
    }
    @Test
    public void testUpdateReceipientFailure(){
        recipientRepository.save(recipient);
        recipient.setId("nandu");
        recipient.setName("nandini");
        recipient.setEmail("nandu@gmail.com");
        recipient.setPassword("nandini");
        recipient.setPhoneNumber(123456);
        recipient.setRole("Receipient");
        String comment = (recipientRepository.findById("nandu").get()).getId();
        Assert.assertNotEquals("nandi", comment);
    }
    @Test
    public void testDeleteReceipientFailure(){
        recipientRepository.save(recipient);
        Recipient fetchRecipient = recipientRepository.findById(recipient.getId()).get();
        Assert.assertNotEquals(null, fetchRecipient.getId());
    }

}
