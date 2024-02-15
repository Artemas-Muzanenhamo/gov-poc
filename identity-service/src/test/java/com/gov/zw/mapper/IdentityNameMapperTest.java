package com.gov.zw.mapper;

import com.gov.zw.json.IdentityNameJson;
import com.gov.zw.domain.IdentityName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gov.zw.mapper.IdentityNameMapper.toIdentityNameDTO;
import static org.assertj.core.api.Assertions.assertThat;

class IdentityNameMapperTest {

    private static final String NAME = "artemas";

    @Test
    @DisplayName("Should map IdentityNameJson to IdentityNameDTO")
    void mapIdentityNameJsonToDTO() {
        IdentityNameJson identityNameJson = new IdentityNameJson(NAME);

        IdentityName name = toIdentityNameDTO(identityNameJson);

        assertThat(name)
                .isNotNull()
                .extracting(IdentityName::getName).isEqualTo(NAME);
    }
}
