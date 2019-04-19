package com.gov.zw.client;

import java.util.Map;

public class IdentityReferenceJson {
    public String idRef;

    public IdentityReferenceJson() { }

    public IdentityReferenceJson(Map<String, String> idRef) {
        this.idRef = idRef.get("ref");
    }

    @Override
    public String toString() {
        return "IdentityReferenceJson{" +
                "idRef='" + idRef + '\'' +
                '}';
    }
}
