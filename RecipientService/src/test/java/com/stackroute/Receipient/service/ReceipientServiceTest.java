package com.stackroute.Receipient.service;

import com.stackroute.Receipient.Domain.Receipient;
import com.stackroute.Receipient.Exception.ReceipientAlreadyExistsException;
import com.stackroute.Receipient.Exception.ReceipientNotFoundException;
import com.stackroute.Receipient.Repository.ReceipientRepository;
import com.stackroute.Receipient.Service.ReceipientServiceImpl;
import org.junit.Assert;
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

public class ReceipientServiceTest {
    Receipient receipient;
    @Mock
    ReceipientRepository receipientRepository;
    @InjectMocks
    ReceipientServiceImpl receipientService;
    List<Receipient> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        receipient = new Receipient();
        receipient.setId("nandu");
        receipient.setName("nandini");
        receipient.setEmail("n@gmail.com");
        receipient.setPassword("nnnnn");
        receipient.setPhoneNumber(123456);
        receipient.setRole("Receipient");
        list = new ArrayList<>();
        list.add(receipient);
    }
    @Test
    public void saveReceipientTestSuccess() throws ReceipientAlreadyExistsException {

        when(receipientRepository.save((Receipient) any())).thenReturn(receipient);
        Receipient savedReceipient = receipientService.saveNewProductOwner(receipient);
        assertEquals(receipient,savedReceipient);

        //verify here verifies that trackRepository save method is only called once
        verify(receipientRepository,times(1)).save(receipient);

    }
    @Test
    public void saveReceipientTestFailure() throws ReceipientAlreadyExistsException {
        when(receipientRepository.save((Receipient) any())).thenReturn(null);

        Receipient savedReceipient = receipientService.saveNewProductOwner(receipient);
        System.out.println("savedReceipient" + savedReceipient);
    }

    @Test
    public void testGetAllReceipients() throws ReceipientNotFoundException {

        receipientRepository.save(receipient);
        //stubbing the mock to return specific data
        when(receipientRepository.findAll()).thenReturn(list);
        List<Receipient> receipientlist = receipientService.getAllOwners();
        assertEquals(list,receipientlist);
    }
    @Test
    public void deleteReceipientTestSuccess() throws ReceipientNotFoundException {

        receipientRepository.delete(receipient);
        boolean deletedReceipient=receipientRepository.existsById("nandu");
        assertEquals(false,deletedReceipient);
    }

    @Test
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
    }
}
