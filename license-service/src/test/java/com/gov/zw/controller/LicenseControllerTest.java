package com.gov.zw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.zw.domain.License;
import com.gov.zw.service.LicenseService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LicenseController.class)
public class LicenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LicenseService licenseServiceImpl;

    @Test
    public void addLicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License("1", "1", "Rodgers", "Mike Oscar", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        Map<String, String> licenseObject = objectMapper.convertValue(license, Map.class);
        JSONObject jsonObject = new JSONObject(licenseObject);

        mockMvc.perform(MockMvcRequestBuilders.post("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllLicenses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/licenses"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateLicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License("1", "1", "Delta", "Charlie Foxtrot", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        Map<String, String> licenseObject = objectMapper.convertValue(license, Map.class);
        JSONObject jsonObject = new JSONObject(licenseObject);

        mockMvc.perform(MockMvcRequestBuilders.put("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteLicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License("1", "1", "Rodgers", "Mike Oscar", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        Map<String, String> id = objectMapper.convertValue(license, Map.class);
        JSONObject jsonObject = new JSONObject(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLicenseByIdentityRef() throws Exception {
        Map<String, String> idRef = new HashMap<>();
        idRef.put("ref", "121");
        JSONObject jsonObject = new JSONObject(idRef);

        mockMvc.perform(MockMvcRequestBuilders.post("/licenses/ref")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }


}
