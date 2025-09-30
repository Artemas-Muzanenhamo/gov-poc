package com.gov.zw.domain;

public record IdentityReference(String idRef) {
    public static IdentityReference empty() {
        return new IdentityReference(null);
    }
}
