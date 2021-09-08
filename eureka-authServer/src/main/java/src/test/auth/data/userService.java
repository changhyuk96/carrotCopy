package src.test.auth.data;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userService implements UserDetailsService{

	@Autowired
	private userDAO dao;
	
	
	public userVO findByUsername(String u_id){
		
		return dao.findByUsername(u_id);
	}
	
	public int signUpUser(Map<String, String> map) {
		return dao.signUpUser(map);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return dao.findByUsername(username);
	}
		
}
