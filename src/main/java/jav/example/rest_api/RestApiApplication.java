package jav.example.rest_api;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@OpenAPIDefinition(info = @Info(title = "DEMO API",version = "2.0",description = "Product Microservice"))
@SpringBootApplication

public class RestApiApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(RestApiApplication.class, args);
	}

}
