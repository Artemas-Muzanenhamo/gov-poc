package com.gov.zw;

import com.gov.zw.configuration.HelloRibbonClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
<<<<<<< HEAD
=======
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
>>>>>>> d9dea1dee7fd33c1d6bc52f2d3dd409675c4396a

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "hello", configuration = HelloRibbonClientConfiguration.class)
public class LicenseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseServiceApplication.class, args);
	}
}
