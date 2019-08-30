package com.stackroute.recipient.service;

import com.stackroute.recipient.domain.Recipient;
import com.stackroute.recipient.exception.RecipientAlreadyExistsException;
import com.stackroute.recipient.exception.RecipientNotFoundException;
import com.stackroute.recipient.repository.RecipientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class RecipientServiceTest {
    Recipient recipient;
    @Mock
    RecipientRepository recipientRepository;
    @InjectMocks
    RecipientServiceImpl receipientService;
    List<Recipient> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        recipient = new Recipient();
        recipient.setId("nandu");
        recipient.setName("nandini");
        recipient.setEmail("n@gmail.com");
        recipient.setPassword("nnnnn");
        recipient.setPhoneNumber(123456);
        recipient.setRole("Receipient");
        list = new ArrayList<>();
        list.add(recipient);
    }
    @Test
    public void saveReceipientTestSuccess() throws RecipientAlreadyExistsException {

        when(recipientRepository.save((Recipient) any())).thenReturn(recipient);
        Recipient savedRecipient = receipientService.saveNewProductOwner(recipient);
        assertEquals(recipient, savedRecipient);

        //verify here verifies that trackRepository save method is only called once
        verify(recipientRepository,times(1)).save(recipient);

    }
    @Test
    public void saveReceipientTestFailure() throws RecipientAlreadyExistsException {
        when(recipientRepository.save((Recipient) any())).thenReturn(null);

        Recipient savedRecipient = receipientService.saveNewProductOwner(recipient);
        System.out.println("savedReceipient" + savedRecipient);
    }

    @Test
    public void testGetAllReceipients() throws RecipientNotFoundException {

        recipientRepository.save(recipient);
        //stubbing the mock to return specific data
        when(recipientRepository.findAll()).thenReturn(list);
        List<Recipient> receipientlist = receipientService.getAllOwners();
        assertEquals(list,receipientlist);
    }
    @Test
    public void deleteReceipientTestSuccess() throws RecipientNotFoundException {

        recipientRepository.delete(recipient);
        boolean deletedReceipient= recipientRepository.existsById("nandu");
        assertEquals(false,deletedReceipient);
    }

    /*@Test
    public void updateReceipientTest() throws ReceipientAlreadyExistsException
    {
        when(receipientRepository.save((Receipient) any())).thenReturn(receipient);
        Receipient updateReceipient = null;
        try {
            updateReceipient = receipientService.saveNewProductOwner(receipient);
        } catch (ReceipientAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(receipient,updateReceipient);
    }*/
}
