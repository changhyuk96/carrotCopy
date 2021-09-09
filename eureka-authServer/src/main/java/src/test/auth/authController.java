package src.test.auth;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import src.test.auth.data.userService;
import src.test.auth.data.userVO;

@CrossOrigin("*")
@RestController
public class authController {

	@Autowired
	private userService userService;
	
	@Autowired
	private JwtBuilder jwtBuilder;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	Logger logger = LoggerFactory.getLogger(authController.class);
	
	@PostMapping("/login")
	public Object login(HttpServletRequest request) {
		
		try {
			
			String jwtToken = request.getHeader("jwtToken");
			
			// Token이 없을 때는 로그인 시도 후 토큰 부여
			if(jwtToken == null) {
				
				userVO userInfo = userService.findByUsername(request.getHeader("u_id"));
				
				// userInfo가 존재할 때
				if(userInfo != null) {
					
					// 암호화된 Pass와 입력한 pass가 일치하면 토큰 생성
					if(passEncoder.matches(request.getHeader("u_password"), userInfo.getPassword())) {
						jwtToken = jwtBuilder.generateToken(userInfo);
					}
					else
						return ResponseEntity.ok().body("패스워드가 일치하지 않습니다.");
				}else
					return ResponseEntity.ok().body("존재하지 않는 아이디입니다.");
				
				logger.info( LocalDateTime.now() + " [ "+userInfo.getU_id()+" ] :: jwt Token 발급");
				return ResponseEntity.ok().header("jwtToken", jwtToken).body(null);
			}
			
			// Access 토큰 유효성검사
			if(jwtBuilder.validateAccessToken(jwtToken).equals("OK")) {
				
				// Access 토큰 있을 시 200
				return ResponseEntity.ok().body(null);
				
			// Access 토큰의 유효성이 없을 때 refresh 토큰 유효성 검사
			}else {
				
			}
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("패스워드를 입력해주십시오.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "failed..";
	}
	
	@PostMapping("/signUp")
	public Object signUp(HttpServletRequest request) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("u_id", request.getParameter("u_id"));
		map.put("u_nickname", request.getParameter("u_nickname"));
		map.put("u_password", passEncoder.encode(request.getParameter("u_password")));
		int result =0;
		try {
			result = userService.signUpUser(map);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok().body("이미 존재하는 아이디입니다.");
		}
		
		if(result == 1)
			return ResponseEntity.ok().body("가입이 완료되었습니다.");
		else
			return ResponseEntity.ok().body("오류가 발생했습니다.");
	}
}
