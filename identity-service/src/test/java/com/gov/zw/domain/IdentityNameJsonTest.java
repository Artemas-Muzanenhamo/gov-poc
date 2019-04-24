package com.gov.zw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentityNameJsonTest {
    private IdentityNameJson identityNameJson;

    @BeforeEach
    void init() {
        identityNameJson = new IdentityNameJson();
    }

    @Test
    void equality_check() {
        identityNameJson = new IdentityNameJson("Artemas");
        IdentityNameJson newIdName = new IdentityNameJson("Artemas");
        assertThat(identityNameJson).isEqualTo(newIdName);
        assertThat(identityNameJson.toString()).isEqualTo(newIdName.toString());
        assertThat(identityNameJson.hashCode()).isEqualTo(newIdName.hashCode());
    }
}
