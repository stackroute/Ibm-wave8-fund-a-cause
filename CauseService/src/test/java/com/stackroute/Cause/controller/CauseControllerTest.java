package com.stackroute.Cause.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.cause.controller.CauseController;
import com.stackroute.cause.domain.Cause;
import com.stackroute.cause.exception.CauseAlreadyExistsException;
import com.stackroute.cause.service.CauseService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import java.util.List;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class CauseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KafkaTemplate<String,Object> kafkaTemplate;
    private Cause cause;
    @MockBean
    private CauseService service;
    @InjectMocks
    private CauseController causeController;
    private List<Cause> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(causeController).build();
        cause = new Cause();
        cause.setId("f123");
        cause.setName("relief");
        cause.setReceiverId("nan123");
        cause.setCauseType("Floods");
        cause.setCauseDescription("aa");
        cause.setTimestamp("21-01-2019");
        cause.setLocation("Chennai");
        cause.setAmount(12345678);
        list = new ArrayList();
        list.add(cause);
    }
    @Test
    public void saveCause() throws Exception {
        when(service.saveNewCause(any())).thenReturn(cause);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cause")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cause)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void saveCauseFailure() throws Exception {
        when(service.saveNewCause(any())).thenThrow(CauseAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cause")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cause)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testgetAllCauses() throws Exception {
        when(service.getAllCauses()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/causes")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cause)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void deleteReceipient() throws Exception
    {

        when(service.deleteCause(anyString())).thenReturn(cause);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cause/f123")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cause)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateCause() throws Exception
    {

        when(service.updateCausedetails(any())).thenReturn(cause);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/cause/relief")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(cause)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
