package com.gov.zw.client;

public record IdentityReferenceJson(String idRef) {
    public static IdentityReferenceJson empty() {
        return new IdentityReferenceJson(null);
    }
}
