package com.gov.zw.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class IdentityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getIdentitiesByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/id/Artemas"))
                .andExpect(status().isOk());
    }

    @Test
    public void getIdentities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/id"))
                .andExpect(status().isOk());
    }

}
