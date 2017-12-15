package com.gov.zw.client;

import com.gov.zw.domain.Identity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "identity-service")
public interface IdentityClient {

    @RequestMapping(method = RequestMethod.POST, value = "/identities", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Identity findIdentityByIdReferenceNumber(String referenceNumber);
}
