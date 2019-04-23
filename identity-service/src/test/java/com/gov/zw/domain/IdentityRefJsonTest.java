package com.gov.zw.domain;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class IdentityRefJsonTest {
    private IdentityRefJson identityRefJson;

    @Test
    void should_produce_a_valid_identity_ref_json() {
        identityRefJson = new IdentityRefJson("1234");
        JSONObject idRefJson = new JSONObject();
        idRefJson.put("idRef", "1234");

        String idRef = identityRefJson.idRef;

        assertThat(idRef).isEqualTo(idRefJson.get("idRef"));
    }

    @Test
    void should_produce_an_empty_identity_ref_json() {
        identityRefJson = new IdentityRefJson();
        JSONObject idRefJson = new JSONObject();
        idRefJson.put("idRef", null);

        String idRef = identityRefJson.idRef;

        assertThat(idRef).isEqualTo(idRefJson.get("idRef"));
    }
}
