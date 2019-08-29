package com.stackroute.DonorRegistration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.DonorRegistration.Controller.DonorController;
import com.stackroute.DonorRegistration.Domain.Donor;
import com.stackroute.DonorRegistration.Exceptions.DonorAlreadyExistsException;
import com.stackroute.DonorRegistration.Service.DonorService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DonorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Donor donor;

    @MockBean
    private DonorService donorService;

    @MockBean
    private KafkaTemplate<String,Object> kafkaTemplate;

    @InjectMocks
    private DonorController donorController;

    private List<Donor> list = null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donorController).build();
        donor = new Donor();
        donor.setId("ash123");
        donor.setName("Ashmita");
        donor.setEmail("ash123@gmail.com");
        donor.setPassword("123456");
        donor.setPhoneNumber(12321444);
        donor.setRole("Donor");
        list = new ArrayList();

        list.add(donor);
    }

    @Test
    public void testSaveNewDonor() throws Exception {
        when(donorService.saveNewDonor(any())).thenReturn(donor);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/donor")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donor)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveNewDonorFailure() throws Exception {
        when(donorService.saveNewDonor(any())).thenThrow(DonorAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/donor")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donor)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testgetAllDonors() throws Exception {
        when(donorService.getAllDonors()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/donors")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donor)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testDeleteDonor() throws Exception {
        when(donorService.deleteById("ash123")).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/donor/ash123")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donor)))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateDonor() throws Exception
    {
        when(donorService.updateById(donor)).thenReturn(donor);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/donor/ash123")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donor)))
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
