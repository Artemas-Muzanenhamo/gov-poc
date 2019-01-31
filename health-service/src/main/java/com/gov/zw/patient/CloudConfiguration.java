package com.gov.zw.patient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.gov.zw.patient")
public class CloudConfiguration {
}
