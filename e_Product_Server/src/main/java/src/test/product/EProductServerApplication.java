package src.test.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("src.test.product.data")
public class EProductServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EProductServerApplication.class, args);
	}

}
