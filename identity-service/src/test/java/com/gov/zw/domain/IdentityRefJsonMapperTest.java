package com.gov.zw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class IdentityRefJsonMapperTest {

    private static final String ID_REFERENCE = "12345";
    private IdentityRefJsonMapper identityRefJsonMapper;
    private IdentityReferenceJson identityRefJson;

    @BeforeEach
    void init() {
        identityRefJsonMapper = new IdentityRefJsonMapper();
    }

    @Test
    void should_convert_identity_ref_json_to_string() {
        identityRefJson = new IdentityReferenceJson(ID_REFERENCE);
        String identityRef = identityRefJsonMapper.toIdentityRef(identityRefJson);
        assertThat(identityRef).isEqualTo(ID_REFERENCE);
    }

    @Test
    void should_convert_null_identity_ref_json_to_string() {
        Map<String, String> idRef = new HashMap<>();
        idRef.put("ideRef", null);
        identityRefJson = new IdentityReferenceJson(idRef);
        String identityRef = identityRefJsonMapper.toIdentityRef(identityRefJson);
        assertThat(identityRef).isEqualTo(null);
    }

    @Test
    void should_convert_empty_identity_ref_json_to_string() {
        identityRefJson = new IdentityReferenceJson("");
        String identityRef = identityRefJsonMapper.toIdentityRef(identityRefJson);
        assertThat(identityRef).isEqualTo("");
    }
}
