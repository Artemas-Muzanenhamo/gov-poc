package com.gov.zw.client.dto;

import java.util.Objects;

public class IdentityReference {
    private final String idRef;

    public IdentityReference(String idRef) {
        this.idRef = idRef;
    }

    public String getIdRef() {
        return idRef;
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
