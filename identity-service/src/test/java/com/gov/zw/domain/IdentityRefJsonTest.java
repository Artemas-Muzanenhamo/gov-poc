package com.gov.zw.domain;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class IdentityRefJsonTest {
    private IdentityReferenceJson identityRefJson;

    @Test
    @DisplayName("Should produce a valid IdentityReferenceJson")
    void getIdentityReference() {
        identityRefJson = new IdentityReferenceJson("1234");
        JSONObject idRefJson = new JSONObject();
        idRefJson.put("idRef", "1234");

        String idRef = identityRefJson.getIdRef();

        assertThat(idRef).isEqualTo(idRefJson.get("idRef"));
    }

    @Test
    @DisplayName("Should produce an empty IdentityReferenceJson")
    void returnEmptyIdentityReferenceJson() {
        identityRefJson = new IdentityReferenceJson();
        JSONObject idRefJson = new JSONObject();
        idRefJson.put("idRef", null);

        String idRef = identityRefJson.getIdRef();

        assertThat(idRef).isEqualTo(idRefJson.get("idRef"));
    }

    @Test
    @DisplayName("Both IdentityReferenceJson objects should be equal")
    void equalityCheck() {
        identityRefJson = new IdentityReferenceJson("12345");

        IdentityReferenceJson newIdentityRef = new IdentityReferenceJson("12345");

        assertThat(identityRefJson).isEqualTo(newIdentityRef);
        assertThat(identityRefJson.toString()).isEqualTo(newIdentityRef.toString());
        assertThat(identityRefJson.hashCode()).isEqualTo(newIdentityRef.hashCode());
    }
}
