package src.test.auth.data;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userDAO{
	
	// 로그인
	public userVO findByUsername(String u_id);
	
	// 회원가입
	public int signUpUser(Map<String, String> map);
}
