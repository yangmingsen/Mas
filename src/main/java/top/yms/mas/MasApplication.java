package top.yms.mas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "top.yms.mas")
public class MasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasApplication.class, args);
	}

}
