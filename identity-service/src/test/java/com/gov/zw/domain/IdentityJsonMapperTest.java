package com.gov.zw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentityJsonMapperTest {
    private IdentityJsonMapper identityJsonMapper;
    private IdentityJson identityJson;
    private Identity identity;

    @BeforeEach
    void init() {
        identityJsonMapper = new IdentityJsonMapper();
        identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
    }

    @Test
    void should_convert_identity_json_to_string() {
        identityJson = new IdentityJson(identity);
        Identity identity = identityJsonMapper.toIdentity(identityJson);

        Identity expectedIdentity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");

        assertThat(identity).isEqualTo(expectedIdentity);
    }

    @Test
    void should_convert_null_identity_json_to_string() {
        identityJson = new IdentityJson();
        Identity identity = identityJsonMapper.toIdentity(identityJson);

        Identity expectedIdentity = new Identity();

        assertThat(identity).isEqualTo(expectedIdentity);
    }

    @Test
    void should_convert_empty_identity_json_to_string() {
        Identity emptyIdentity = new Identity();
        identityJson = new IdentityJson(emptyIdentity);
        Identity identity = identityJsonMapper.toIdentity(identityJson);

        Identity expectedIdentity = new Identity();

        assertThat(identity).isEqualTo(expectedIdentity);
    }
}
