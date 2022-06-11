package top.yms.mas;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@MapperScan(basePackages = "top.yms.mas")
public class MasApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(MasApplication.class, args);

		Environment env = applicationContext.getEnvironment();
		String port = env.getProperty("server.port");
		String name = env.getProperty("spring.application.name");

		String path = env.getProperty("server.servlet.context-path");
		if (StringUtils.isBlank(path)) {
			path = "";
		}
		String ip = "127.0.0.1";

		System.out.println("\n----------------------------------------------------------\n\t" +
				name.toUpperCase() + " is running! Access URLs:\n\t" +
				"Local: \t\thttp://" + ip + ":" + port + path + "/\n\t" +
				"swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
				"actuator: \thttp://" + ip + ":" + port + path + "/actuator/info\n\t" +
				"----------------------------------------------------------");
	}

}
