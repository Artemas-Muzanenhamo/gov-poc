package com.gov.zw.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.zw.dto.License;
import com.gov.zw.service.LicenseService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LicenseController.class)
class LicenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LicenseService licenseServiceImpl;

    private final TypeReference<Map<String, String>> licenseTypeRef = new TypeReference<Map<String, String>>() {};

    @Test
    @DisplayName("Should add a license given a valid License")
    void shouldAddALicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License("1", "1", "Rodgers", "Mike Oscar", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        Map<String, String> licenseObject = objectMapper.convertValue(license, licenseTypeRef);
        JSONObject jsonObject = new JSONObject(licenseObject);

        mockMvc.perform(MockMvcRequestBuilders.post("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should throw HttpStatus BAD_REQUEST when an invalid license object is passed")
    void shouldThrowBadRequestTryingToGetLicense() throws Exception {
        JSONObject license = null;

        mockMvc.perform(MockMvcRequestBuilders.post("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("null"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return status OK when an empty License is passed attempting to add a license")
    void shouldReturn200WhenAnEmptyLicenseIsPassedToAddLicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License();
        Map<String, String> licenseObject = objectMapper.convertValue(license, licenseTypeRef);
        JSONObject jsonObject = new JSONObject(licenseObject);

        mockMvc.perform(MockMvcRequestBuilders.post("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should retrieve all licenses")
    void shouldReturnAllLicenses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/licenses"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should update a License given a valid license object with updated values")
    void shouldUpdateALicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License("1", "1", "Delta", "Charlie Foxtrot", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        Map<String, String> licenseObject = objectMapper.convertValue(license, licenseTypeRef);
        JSONObject jsonObject = new JSONObject(licenseObject);

        mockMvc.perform(MockMvcRequestBuilders.put("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return HttpStatus OK when an empty license is passed when attempting to update a license")
    void shouldReturn200WhenAnEmptyLicenseIsPassedToUpdateLicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License();
        Map<String, String> licenseObject = objectMapper.convertValue(license, licenseTypeRef);
        JSONObject jsonObject = new JSONObject(licenseObject);


        mockMvc.perform(MockMvcRequestBuilders.put("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete a license given a valid license object")
    void shouldDeleteALicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License("1", "1", "Rodgers", "Mike Oscar", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        Map<String, String> id = objectMapper.convertValue(license, licenseTypeRef);
        JSONObject jsonObject = new JSONObject(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return HttpStatus 200 when an empty license object is passed attempting to delete a license")
    void shouldReturn200WhenAnEmptyLicenseIsPassedToDeleteLicense() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License();
        Map<String, String> licenseObject = objectMapper.convertValue(license, licenseTypeRef);
        JSONObject jsonObject = new JSONObject(licenseObject);

        mockMvc.perform(MockMvcRequestBuilders.delete("/licenses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return License details given an identity reference number")
    void shouldReturnLicenseByIdentityRef() throws Exception {
        Map<String, String> idRef = new HashMap<>();
        idRef.put("ref", "121");
        JSONObject jsonObject = new JSONObject(idRef);

        mockMvc.perform(MockMvcRequestBuilders.post("/licenses/ref")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return HttpStatus OK when an empty license is passed attempting to get a license by identity reference")
    void shouldReturn200WhenAnEmptyLicenseIsPassedToGetLicenseByIdReference() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        License license = new License();
        Map<String, String> licenseObject = objectMapper.convertValue(license, licenseTypeRef);
        JSONObject jsonObject = new JSONObject(licenseObject);

        mockMvc.perform(MockMvcRequestBuilders.post("/licenses/ref")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }


}
