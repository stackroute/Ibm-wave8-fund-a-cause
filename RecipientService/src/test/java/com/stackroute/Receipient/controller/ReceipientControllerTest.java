package com.stackroute.Receipient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Receipient.Controller.ReceipientController;
import com.stackroute.Receipient.Domain.Receipient;
import com.stackroute.Receipient.Exception.ReceipientAlreadyExistsException;
import com.stackroute.Receipient.Service.ReceipientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
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
public class ReceipientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Receipient receipient;
    @MockBean
    private KafkaTemplate<String,Object> kafkaTemplate;
    @MockBean
    private ReceipientService receipientService;
    @InjectMocks
    private ReceipientController receipientController;
    private List<Receipient> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(receipientController).build();
        receipient = new Receipient();
        receipient.setId("nandu");
        receipient.setName("nandini");
        receipient.setEmail("n@gmail.com");
        receipient.setPassword("nnnnn");
        receipient.setPhoneNumber(123456);
        receipient.setRole("Receipient");
        list = new ArrayList();
        list.add(receipient);
    }
    @Test
    public void saveReceipient() throws Exception {
        when(receipientService.saveNewProductOwner(any())).thenReturn(receipient);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/owner")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(receipient)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void saveReceipientFailure() throws Exception {
        when(receipientService.saveNewProductOwner(any())).thenThrow(ReceipientAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/owner")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(receipient)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testgetAllReceipients() throws Exception {
        when(receipientService.getAllOwners()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/owners")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(receipient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void deleteReceipient() throws Exception
    {

        when(receipientService.deleteOwner(anyString())).thenReturn(receipient);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/owner/nandu")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(receipient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateReceipient() throws Exception
    {

        when(receipientService.updateOwnerdetails(any())).thenReturn(receipient);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/owner/nandini")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(receipient)))
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
