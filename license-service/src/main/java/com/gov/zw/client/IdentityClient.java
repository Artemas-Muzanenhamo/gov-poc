package com.gov.zw.client;


import com.gov.zw.client.dto.IdentityReference;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "identity-service")
@Component
public interface IdentityClient {

    @PostMapping(value = "/identities/reference", produces = APPLICATION_JSON_VALUE)
    Identity findIdentityByIdReferenceNumber(@RequestBody IdentityReference identityReference);
}
