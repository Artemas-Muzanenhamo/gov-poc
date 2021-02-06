package com.gov.zw.mapper;

import com.gov.zw.domain.IdentityReferenceJson;
import com.gov.zw.dto.IdentityReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.gov.zw.mapper.IdentityReferenceMapper.toIdentityRefDTO;
import static org.assertj.core.api.Java6Assertions.assertThat;

class IdentityReferenceMapperTest {

    private static final String ID_REFERENCE = "MUZAN12345";

    @Test
    @DisplayName("Should map IdentityReferenceJson to IdentityReferenceDTO")
    void mapIdentityReferenceJsonToDTO() {
        Map<String, String> idReference = new HashMap<>();
        idReference.put("idRef", ID_REFERENCE);
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(idReference);

        IdentityReference identityReference = toIdentityRefDTO(identityReferenceJson);

        assertThat(identityReference).isNotNull();
        assertThat(identityReference.getIdRef()).isEqualTo(ID_REFERENCE);
    }

    @Test
    @DisplayName("Should return empty IdentityReferenceDTO when IdentityReferenceJson is null")
    void mapNullIdentityReferenceJsonToEmptyDTO() {
        IdentityReference identityReference = toIdentityRefDTO(null);

        assertThat(identityReference).isNotNull();
    }
}