package jav.example.rest_api;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "DEMO API",version = "2.0",description = "Product Microservice"))
@SpringBootApplication

public final class RestApiApplication {

	public static void main(String[] args)
	{

		SpringApplication.run(RestApiApplication.class, args);
	}
	private RestApiApplication()
	{
		//default constructor
	}

}
