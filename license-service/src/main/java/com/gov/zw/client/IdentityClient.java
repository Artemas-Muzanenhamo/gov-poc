package com.gov.zw.client;

import com.gov.zw.domain.Identity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "identity-service")
public interface IdentityClient {

    @PostMapping(value = "/identities/reference")
    Identity findIdentityByIdReferenceNumber(@RequestBody Map<String, String> referenceNumber);
}
