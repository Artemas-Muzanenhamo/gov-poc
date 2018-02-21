package com.gov.zw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.zw.domain.Identity;
import com.gov.zw.service.IdentityService;
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
@WebMvcTest(IdentityController.class)
public class IdentityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IdentityService identityServiceImpl;

    @Test
    public void saveIdentity() throws Exception {
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
    public void getIdentitiesByName() throws Exception {
        Map<String, String> name = new HashMap<>();
        name.put("name", "Artemas");
        JSONObject jsonObject = new JSONObject(name);
        mockMvc.perform(MockMvcRequestBuilders.post("/identities/name")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    public void getIdentityByReferenceNumber() throws Exception {
        Map<String, String> idReferenceNumber = new HashMap<>();
        idReferenceNumber.put("idRef", "1");
        JSONObject jsonObject = new JSONObject(idReferenceNumber);

        mockMvc.perform(MockMvcRequestBuilders.post("/identities/reference")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllIdentities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/identities"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateIdentity() throws Exception {
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
    public void deleteIdentity() throws Exception {
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

}
