package com.stackroute.recipient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recipient.domain.Recipient;
import com.stackroute.recipient.exception.RecipientAlreadyExistsException;
import com.stackroute.recipient.service.RecipientService;
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
public class RecipientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Recipient recipient;
    @MockBean
    private KafkaTemplate<String,Object> kafkaTemplate;
    @MockBean
    private RecipientService recipientService;
    @InjectMocks
    private RecipientController recipientController;
    private List<Recipient> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipientController).build();
        recipient = new Recipient();
        recipient.setId("nandu");
        recipient.setName("nandini");
        recipient.setEmail("n@gmail.com");
        recipient.setPassword("nnnnn");
        recipient.setPhoneNumber(123456);
        recipient.setRole("Receipient");
        list = new ArrayList();
        list.add(recipient);
    }
    @Test
    public void saveReceipient() throws Exception {
        when(recipientService.saveNewProductOwner(any())).thenReturn(recipient);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/owner")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recipient)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void saveReceipientFailure() throws Exception {
        when(recipientService.saveNewProductOwner(any())).thenThrow(RecipientAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/owner")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recipient)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testgetAllReceipients() throws Exception {
        when(recipientService.getAllOwners()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/owners")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recipient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void deleteReceipient() throws Exception
    {

        when(recipientService.deleteOwner(anyString())).thenReturn(recipient);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/owner/nandu")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recipient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateReceipient() throws Exception
    {

        when(recipientService.updateOwnerdetails(any())).thenReturn(recipient);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/owner/nandini")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recipient)))
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
