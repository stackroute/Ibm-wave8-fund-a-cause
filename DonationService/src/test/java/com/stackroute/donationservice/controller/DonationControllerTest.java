package com.stackroute.donationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.donationservice.domain.Donation;
import com.stackroute.donationservice.service.DonationService;
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
public class DonationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Donation donation;

    @MockBean
    private DonationService donationService;

//    @MockBean
//    private KafkaTemplate<String,Object> kafkaTemplate;

    @InjectMocks
    private DonationController donationController;

    private List<Donation> list = null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();
        donation = new Donation();
        donation.setDonationId("2");
        donation.setReceiverId("22");
        donation.setDonorId("222");
        donation.setCauseId("2222");
        donation.setAmount(22222L);
        list = new ArrayList();

        list.add(donation);
    }

    @Test
    public void testSaveNewDonation() throws Exception {
        when(donationService.saveNewDonation(any())).thenReturn(donation);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/donation")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donation)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void saveNewDonationFailure() throws Exception {
//        when(donationService.saveNewDonation(any())).thenThrow(DonationAlreadyExistsException.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/donation")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donation)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void testgetAllDonation() throws Exception {
        when(donationService.getAllDonation()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/donations")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(donation)))
                .andExpect(MockMvcResultMatchers.status().isOk())
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


