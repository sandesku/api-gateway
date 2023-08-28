package com.gateway.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("path_route",
//						r -> r.path("/product").and().method("POST", "PUT", "DELETE").and()
//								.host("vendor.test-gateway-store.com").uri("http://localhost:8081"))
//				.route("path_route", r -> r.path("/product/**").and().method("GET").uri("http://localhost:8082"))
//				.build();
//	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_route",
						r -> r.path("/product").and().method("POST", "PUT", "DELETE").and().host("localhost*")
								.uri("http://localhost:8081"))
				.route("path_route", r -> r.path("/product/**").and().method("GET")
						.filters(f -> f.addResponseHeader("Cache-Control", "max-age=300")).uri("http://localhost:8082"))
				.build();
	}

}
