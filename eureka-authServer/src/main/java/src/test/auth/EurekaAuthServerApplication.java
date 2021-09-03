package src.test.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("src.test.auth.data")
public class EurekaAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaAuthServerApplication.class, args);
	}

}
