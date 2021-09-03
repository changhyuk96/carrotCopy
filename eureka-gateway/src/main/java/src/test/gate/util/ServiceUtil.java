package src.test.gate.util;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class ServiceUtil {
	
	private final String secret_key = "sec_api_token";
	
	public static String username;

	// Access 토큰 유효성 검사
	public void validateAccessToken(String token) throws Exception{
		Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
	}

	public String getSubject(String token) {
		
		return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getSubject();
	}
}
