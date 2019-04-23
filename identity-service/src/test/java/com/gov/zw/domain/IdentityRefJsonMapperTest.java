package com.gov.zw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class IdentityRefJsonMapperTest {

    private IdentityRefJsonMapper identityRefJsonMapper;
    private IdentityRefJson identityRefJson;

    @BeforeEach
    void init() {
        identityRefJsonMapper = new IdentityRefJsonMapper();
    }

    @Test
    void should_convert_identity_ref_json_to_string() {
        identityRefJson = new IdentityRefJson("12345");
        String identityRef = identityRefJsonMapper.toIdentityRef(identityRefJson);
        assertThat(identityRef).isEqualTo("12345");
    }

    @Test
    void should_convert_null_identity_ref_json_to_string() {
        identityRefJson = new IdentityRefJson(null);
        String identityRef = identityRefJsonMapper.toIdentityRef(identityRefJson);
        assertThat(identityRef).isEqualTo(null);
    }
}
