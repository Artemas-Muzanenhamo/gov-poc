package com.gov.zw.client;

import java.util.Map;

public class IdentityReferenceJson {
    public String ref;

    public IdentityReferenceJson() { }

    public IdentityReferenceJson(Map<String, String> ref) {
        this.ref = ref.get("ref");
    }
}
