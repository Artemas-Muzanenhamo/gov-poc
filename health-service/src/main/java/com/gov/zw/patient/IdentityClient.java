package com.gov.zw.patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "identity-service")
@Component
public interface IdentityClient {

    @PostMapping(value = "/identities/reference")
    Identity findIdentityByIdReferenceNumber(@RequestBody Map<String, String> referenceNumber);
}
