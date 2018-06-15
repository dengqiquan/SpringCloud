package com.fcore.cloud.zuul;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.fcore.cloud.zuul.filter.AccessFilter;

/**
 * @SpringCloudApplication包含：
 * @SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
 *
 */
@SpringCloudApplication
@EnableZuulProxy
@RefreshScope
@RestController
public class ZuulApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
	}
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}
