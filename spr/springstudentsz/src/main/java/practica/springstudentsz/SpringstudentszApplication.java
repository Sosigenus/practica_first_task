package practica.springstudentsz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringstudentszApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringstudentszApplication.class, args);
	}

}