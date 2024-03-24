package com.gov.zw.client;

import lombok.Getter;

import java.util.Objects;

@Getter
public class IdentityReferenceJson {
    public String idRef;

    public IdentityReferenceJson() { }

    public IdentityReferenceJson(String idRef) {
        this.idRef = idRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityReferenceJson that = (IdentityReferenceJson) o;
        return Objects.equals(idRef, that.idRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRef);
    }

    @Override
    public String toString() {
        return "IdentityReferenceJson{" +
                "idRef='" + idRef + '\'' +
                '}';
    }
}
