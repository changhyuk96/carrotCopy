package src.test.auth.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userVO implements UserDetails {

	private String u_id;
	private String u_nickname;
	private String u_password;
	private String u_reg_date;
	private Collection<GrantedAuthority> u_authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return u_authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u_password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return u_id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
