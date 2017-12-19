package com.gov.zw.configuration;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class HelloRibbonClientConfiguration {

	@Bean
    public ILoadBalancer ribbonLoadBalancer() {
		//because of this, it doesn't use eureka to lookup the server,
		// but the classpath is tested
		BaseLoadBalancer balancer = new BaseLoadBalancer();
		balancer.setServersList(Arrays.asList(new Server("localhost", 8081)));
		return balancer;
	}

}