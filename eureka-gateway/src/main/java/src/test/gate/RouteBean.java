package src.test.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import src.test.gate.filter.AuthFilter;
import src.test.gate.util.ServiceUtil;

@Configuration
public class RouteBean {
	
	@Autowired
	private AuthFilter authFilter;

	@Bean
	public RouteLocator gatewayRoute(RouteLocatorBuilder builder) {
		
		return builder.routes()
					.route(r -> r.path("/auth/**")
								 .filters(f -> f.rewritePath("/auth/(?<path>.*)", "/${path}"))
								 .uri("http://172.30.1.34:8010")
							)
					
					.route(r -> r.path("/api/chat/**")
								.filters(f -> f.rewritePath("/api/chat/(?<path>.*)", "/${path}")
								 .filter(authFilter))
								 .uri("lb://chat-api")
							)
					
					.route(r -> r.path("/api/product/**")
								.filters(f -> f.rewritePath("/api/product/(?<path>.*)", "/${path}")
								.filter(authFilter))
								.uri("lb://product-api")
							)
					
					.build();
								 
	}
}
