package com.gov.zw.mapper;

import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.client.exception.IdentityReferenceJsonNotValidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gov.zw.mapper.IdentityReferenceMapper.toIdentityReferenceDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentityReferenceMapperTest {

    private static final String ID_REF = "some reference";

    @Test
    @DisplayName("Should map IdentityReferenceJson to IdentityReferenceDTO")
    void mapIdentityReferenceJsonToDTO() {
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(ID_REF);

        IdentityReference identityReference = toIdentityReferenceDTO(identityReferenceJson);

        assertThat(identityReference).isNotNull();
        String idRef = identityReference.getIdRef();
        assertThat(idRef).isEqualTo(ID_REF);
    }

    @Test
    @DisplayName("Should throw IdentityReferenceJsonNotValid exception when IdentityReferenceJson is null")
    void throwExceptionWhenIdentityReferenceJsonIsNull() {
        IdentityReferenceJsonNotValidException exception = assertThrows(IdentityReferenceJsonNotValidException.class, () -> toIdentityReferenceDTO(null));

        assertThat(exception.getMessage()).isEqualTo("Identity Reference is not valid!");
    }

    @Test
    @DisplayName("Should throw IdentityReferenceJsonNotValid exception when identity reference is null")
    void throwExceptionWhenIdentityReferenceIsNull() {
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson();

        IdentityReferenceJsonNotValidException exception = assertThrows(IdentityReferenceJsonNotValidException.class, () -> toIdentityReferenceDTO(identityReferenceJson));

        assertThat(exception.getMessage()).isEqualTo("Identity Reference is not valid!");
    }
}