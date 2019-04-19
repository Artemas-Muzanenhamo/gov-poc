package com.gov.zw.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "identity-service")
@Component
public interface IdentityClient {

    @PostMapping(value = "/identities/reference")
    Identity findIdentityByIdReferenceNumber(@RequestBody IdentityReferenceJson identityReferenceJson);
}
