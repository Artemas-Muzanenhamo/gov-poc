package com.gov.zw.domain;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class IdentityRefJsonTest {
    private IdentityReferenceJson identityRefJson;

    @Test
    void should_produce_a_valid_identity_ref_json() {
        identityRefJson = new IdentityReferenceJson("1234");
        JSONObject idRefJson = new JSONObject();
        idRefJson.put("idRef", "1234");

        String idRef = identityRefJson.idRef;

        assertThat(idRef).isEqualTo(idRefJson.get("idRef"));
    }

    @Test
    void should_produce_an_empty_identity_ref_json() {
        identityRefJson = new IdentityReferenceJson();
        JSONObject idRefJson = new JSONObject();
        idRefJson.put("idRef", null);

        String idRef = identityRefJson.idRef;

        assertThat(idRef).isEqualTo(idRefJson.get("idRef"));
    }

    @Test
    void equality_check() {
        identityRefJson = new IdentityReferenceJson("12345");
        IdentityReferenceJson newIdentityRef = new IdentityReferenceJson("12345");
        assertThat(identityRefJson).isEqualTo(newIdentityRef);
        assertThat(identityRefJson.toString()).isEqualTo(newIdentityRef.toString());
        assertThat(identityRefJson.hashCode()).isEqualTo(newIdentityRef.hashCode());
    }
}
