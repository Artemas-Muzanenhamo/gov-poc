package com.gov.zw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class IdentityNameJsonMapperTest {
    private IdentityNameJsonMapper identityNameJsonMapper;
    private IdentityNameJson identityNameJson;

    @BeforeEach
    void init() {
        identityNameJsonMapper = new IdentityNameJsonMapper();
    }

    @Test
    void should_convert_identity_name_json_to_string() {
        identityNameJson = new IdentityNameJson("12345");
        String identityRef = identityNameJsonMapper.toIdentityName(identityNameJson);
        assertThat(identityRef).isEqualTo("12345");
    }

    @Test
    void should_convert_null_identity_name_json_to_string() {
        identityNameJson = new IdentityNameJson(null);
        String identityRef = identityNameJsonMapper.toIdentityName(identityNameJson);
        assertThat(identityRef).isEqualTo(null);
    }

    @Test
    void should_convert_empty_identity_name_json_to_string() {
        identityNameJson = new IdentityNameJson("");
        String identityRef = identityNameJsonMapper.toIdentityName(identityNameJson);
        assertThat(identityRef).isEqualTo("");
    }
}
