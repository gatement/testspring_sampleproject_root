package lgh.app.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages="lgh")
@EnableSwagger2
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
