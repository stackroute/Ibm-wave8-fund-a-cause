package com.stackroute.Cause.service;

import com.stackroute.cause.domain.Cause;
import com.stackroute.cause.exception.CauseAlreadyExistsException;
import com.stackroute.cause.exception.CauseNotFoundException;
import com.stackroute.cause.repository.CauseRepository;
import com.stackroute.cause.service.CauseServiceImpl;
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

public class CauseServiceTest {
    Cause cause;
    @Mock
    CauseRepository causeRepository;
    @InjectMocks
    CauseServiceImpl causeService;
    List<Cause> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        cause = new Cause();
        cause.setId("f123");
        cause.setName("relief");
        cause.setReceiverId("nan123");
        cause.setCauseType("Floods");
        cause.setCauseDescription("aa");
        cause.setTimestamp("21-01-2019");
        cause.setLocation("Chennai");
        cause.setAmount(12345678);
        list = new ArrayList<>();
        list.add(cause);
    }
    @Test
    public void saveCauseTestSuccess() throws CauseAlreadyExistsException {

        when(causeRepository.save((Cause) any())).thenReturn(cause);
        Cause savedCause = causeService.saveNewCause(cause);
        assertEquals(cause,savedCause);

        //verify here verifies that trackRepository save method is only called once
        verify(causeRepository,times(1)).save(cause);

    }
    @Test
    public void saveCauseTestFailure() throws CauseAlreadyExistsException {
        when(causeRepository.save((Cause) any())).thenReturn(null);

        Cause savedCause = causeService.saveNewCause(cause);

    }

    @Test
    public void testGetAllCauses() throws CauseNotFoundException {

        causeRepository.save(cause);
        //stubbing the mock to return specific data
        when(causeRepository.findAll()).thenReturn(list);
        List<Cause> causelist = causeService.getAllCauses();
        assertEquals(list,causelist);
    }
    @Test
    public void deleteCauseTestSuccess() throws CauseNotFoundException {

        causeRepository.delete(cause);
        boolean deletedCause=causeRepository.existsById("f123");
        assertEquals(false,deletedCause);
    }

    @Test
    public void updateCauseTest() throws CauseAlreadyExistsException
    {
        when(causeRepository.save((Cause) any())).thenReturn(cause);
        Cause update = null;
        try {
            update = causeService.saveNewCause(cause);
        } catch (CauseAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(cause,update);
    }
}
