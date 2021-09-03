package src.test.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()				// HttpServletRequest 요청 URL에 따라 접근권한을 설정함.
				.antMatchers("/shop/**").authenticated();
												// anonymous() = 인증되지 않은 유저만 접근.
		http.formLogin()
			.loginPage("/login")				// 커스텀 로그인 페이지 경로
			.defaultSuccessUrl("/") 			// 인증 성공 시 이동 페이지
			.permitAll();
		
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 경로 지정
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);								// 로그아웃 성공 시 세션 제거
		
		http
			.cors().disable()					// cors 방지
			.csrf().disable()					// csrf 방지
			.headers().frameOptions().disable(); // X-Frame-Option 비활성화
			
	    http.addFilterBefore(new CheckCookieFilter(), BasicAuthenticationFilter.class); 

	}
	
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
