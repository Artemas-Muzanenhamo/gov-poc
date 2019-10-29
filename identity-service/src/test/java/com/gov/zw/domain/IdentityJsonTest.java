package com.gov.zw.domain;

import com.gov.zw.dto.Identity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentityJsonTest {
    private IdentityJson identityJson;

    @Test
    void equality_check() {
        identityJson = new IdentityJson();
        IdentityJson newIdentity = new IdentityJson();
        assertThat(identityJson).isEqualTo(newIdentity);
        assertThat(identityJson.toString()).isEqualTo(newIdentity.toString());
        assertThat(identityJson.hashCode()).isEqualTo(newIdentity.hashCode());
    }

    @Test
    void should_produce_a_valid_identity_json_from_a_valid_identity() {
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        identityJson = new IdentityJson(identity);
        IdentityJson newIdentity = new IdentityJson(identity);
        assertThat(identityJson).isEqualTo(newIdentity);
    }
}
