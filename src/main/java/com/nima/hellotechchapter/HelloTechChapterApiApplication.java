package com.nima.hellotechchapter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Hello Tech Chapter API",
        version = "1.0.0",
        description = "Tech Chapter praktikant-udfordring – Nima Salami"
    )
)
public class HelloTechChapterApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloTechChapterApiApplication.class, args);
    }
}
