package com.gov.zw.json;

import java.util.Map;
import java.util.Objects;

public class IdentityReferenceJson {
    private String idRef;

    public IdentityReferenceJson() { }

    public IdentityReferenceJson(Map<String, String> idRef) {
        this.idRef = idRef.get("idRef");
    }

    public IdentityReferenceJson(String idRef) {
        this.idRef = idRef;
    }

    public String getIdRef() {
        return idRef;
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
