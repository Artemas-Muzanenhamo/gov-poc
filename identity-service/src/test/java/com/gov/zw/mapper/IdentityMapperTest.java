package com.gov.zw.mapper;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gov.zw.mapper.IdentityMapper.toIdentityDTO;
import static org.assertj.core.api.Java6Assertions.assertThat;

class IdentityMapperTest {
    @Test
    @DisplayName("Should map IdentityJson to Identity")
    void mapIdentityJsonToDTO() {
        IdentityJson identityJson = new IdentityJson();

        Identity identity = toIdentityDTO(identityJson);

        assertThat(identity).isNotNull();
    }
}