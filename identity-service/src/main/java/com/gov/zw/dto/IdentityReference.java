package com.gov.zw.dto;

import java.util.Objects;

public class IdentityReference {
    private String idRef;

    public IdentityReference() { }

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
