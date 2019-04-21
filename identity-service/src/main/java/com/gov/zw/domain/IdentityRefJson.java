package com.gov.zw.domain;

import java.util.Objects;

public class IdentityRefJson {
    public String idRef;

    public IdentityRefJson() { }

    public IdentityRefJson(String idRef) {
        this.idRef = idRef;
    }

    @Override
    public String toString() {
        return "IdentityRefJson{" +
                "idRef='" + idRef + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityRefJson that = (IdentityRefJson) o;
        return Objects.equals(idRef, that.idRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRef);
    }
}
