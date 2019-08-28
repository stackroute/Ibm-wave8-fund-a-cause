package com.stackroute.CauseCRUD.service;

import com.stackroute.CauseCRUD.Domain.CauseCRUD;
import com.stackroute.CauseCRUD.Exception.CauseAlreadyExistsException;
import com.stackroute.CauseCRUD.Exception.CauseNotFoundException;
import com.stackroute.CauseCRUD.Repository.CauseCRUDRepository;
import com.stackroute.CauseCRUD.Service.CauseCRUDServiceImpl;
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
    CauseCRUD cause;
    @Mock
    CauseCRUDRepository causeRepository;
    @InjectMocks
    CauseCRUDServiceImpl causeService;
    List<CauseCRUD> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        cause = new CauseCRUD();
        cause.setId("f123");
        cause.setName("relief");
        cause.setReceiverId("nan123");
        cause.setCauseType("Floods");
        cause.setCauseDescription("aa");
        cause.setTimestamp("21-01-2019");
        cause.setLocation("Chennai");
        list = new ArrayList<>();
        list.add(cause);
    }
    @Test
    public void saveCauseTestSuccess() throws CauseAlreadyExistsException {

        when(causeRepository.save((CauseCRUD) any())).thenReturn(cause);
        CauseCRUD savedCause = causeService.saveNewCause(cause);
        assertEquals(cause,savedCause);

        //verify here verifies that trackRepository save method is only called once
        verify(causeRepository,times(1)).save(cause);

    }
    @Test
    public void saveCauseTestFailure() throws CauseAlreadyExistsException {
        when(causeRepository.save((CauseCRUD) any())).thenReturn(null);

        CauseCRUD savedCause = causeService.saveNewCause(cause);

    }

    @Test
    public void testGetAllCauses() throws CauseNotFoundException {

        causeRepository.save(cause);
        //stubbing the mock to return specific data
        when(causeRepository.findAll()).thenReturn(list);
        List<CauseCRUD> causelist = causeService.getAllCauses();
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
        when(causeRepository.save((CauseCRUD) any())).thenReturn(cause);
        CauseCRUD update = null;
        try {
            update = causeService.saveNewCause(cause);
        } catch (CauseAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(cause,update);
    }
}
