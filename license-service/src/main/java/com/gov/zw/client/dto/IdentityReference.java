package com.gov.zw.client.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class IdentityReference {
    private final String idRef;

    public IdentityReference(String idRef) {
        this.idRef = idRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityReference that = (IdentityReference) o;
        return Objects.equals(idRef, that.idRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRef);
    }
}
