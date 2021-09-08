package src.test.gate.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
import src.test.gate.util.ServiceUtil;

@Component
public class AuthFilter implements GatewayFilter{
	
	Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	@Autowired
	private ServiceUtil serviceUtil;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		try {
			String token = exchange.getRequest().getCookies().getFirst("jwtToken").getValue();
			serviceUtil.validateAccessToken(token);
		}
		catch(Exception e) {
			logger.info("CustomAuthFilter ::: Token Error ::: " + e.getCause() +" " + e.getLocalizedMessage());
			return tokenHandler(exchange);
		}
		
		return chain.filter(exchange);
	}
	
    private Mono<Void> tokenHandler(ServerWebExchange exchange){
    	ServerHttpResponse response = exchange.getResponse();
    	
    	response.setStatusCode(HttpStatus.UNAUTHORIZED);
    	return response.setComplete();
    }

}
