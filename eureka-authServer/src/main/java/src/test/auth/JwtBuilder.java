package src.test.auth;

import java.util.Date;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import src.test.auth.data.userVO;

@Configuration
public class JwtBuilder {
	
	private String secret_key="sec_api_token";
	
	private long jwtTokenValidation = 60 * 20 * 1000L; // 20분

	public Claims getClaims(String token) {
		
		try {
			Claims body = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
			
			return body;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String getSubject(String token) {
		
		return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getSubject();
	}
	
	// Access 토큰 생성
	public String generateToken(userVO userInfo) {
		
		Claims claims = Jwts.claims().setSubject(userInfo.getU_id());
		long nowTime = System.currentTimeMillis();
		long expiryTime = nowTime + jwtTokenValidation;

		userInfo.setU_password(null);
	    return Jwts.builder()
	    		.setClaims(claims) // 정보 저장
	    		.setIssuedAt(new Date(nowTime)) // 토큰 발행 시간
	    		.setExpiration(new Date(expiryTime)) // 토큰 만료 시간
	    		.claim("u_id", userInfo.getU_id())
	    		.claim("u_username", userInfo.getUsername())
	    		.signWith(SignatureAlgorithm.HS512, secret_key).compact();
		
	}
	
	// Access 토큰 유효성 검사
	public String validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
			
			return "OK";
		} catch (SignatureException ex) {
			return new String("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			return new String("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			return new String("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			return new String("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			return new String("JWT claims string is empty.");
		}
	}
}
