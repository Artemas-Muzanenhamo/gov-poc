package com.gov.zw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.zw.domain.*;
import com.gov.zw.service.IdentityService;
import net.minidev.json.JSONObject;
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
@WebMvcTest(IdentityController.class)
class IdentityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IdentityService identityServiceImpl;

    @Test
    void saveIdentity() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Identity identity = new Identity("1","1","Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        Map<String, String> id = objectMapper.convertValue(identity, Map.class);
        JSONObject jsonObject = new JSONObject(id);

        mockMvc.perform(MockMvcRequestBuilders.post("/identities")
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getIdentitiesByName() throws Exception {
        Map<String, String> name = new HashMap<>();
        name.put("name", "Artemas");
        IdentityNameJson identityNameJsonjson = new IdentityNameJson("Artemas");
        String json = asJsonString(identityNameJsonjson);
        mockMvc.perform(MockMvcRequestBuilders.post("/identities/name")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void getIdentityByReferenceNumber() throws Exception {
        Map<String, String> idReferenceNumber = new HashMap<>();
        idReferenceNumber.put("idRef", "1");
        IdentityReferenceJson identityReferenceJsonjson = new IdentityReferenceJson(idReferenceNumber);
        String json = asJsonString(identityReferenceJsonjson);

        mockMvc.perform(MockMvcRequestBuilders.post("/identities/reference")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void getAllIdentities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/identities"))
                .andExpect(status().isOk());
    }

    @Test
    void updateIdentity() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Identity identity = new Identity("1","1","Takudzwa", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        Map<String, String> id = objectMapper.convertValue(identity, Map.class);
        JSONObject jsonObject = new JSONObject(id);
        mockMvc.perform(MockMvcRequestBuilders.put("/identities")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteIdentity() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Identity identity = new Identity("1","1","Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        Map<String, String> id = objectMapper.convertValue(identity, Map.class);
        JSONObject jsonObject = new JSONObject(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/identities")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(value);
    }

}
