package com.gov.zw.client;

import com.gov.zw.domain.Identity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "identity-service")
public interface IdentityServiceClient {

    @GetMapping(value = "/id/all", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Identity retrieveIdentity(String identityReference);

}
