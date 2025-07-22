package com.example.xphr;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Ó API",
                version = "1.0",
                description = "API documentation for the XPHR")
)
@SpringBootApplication
public class XPHRApplication {

    public static void main(String[] args) {
        SpringApplication.run(XPHRApplication.class, args);
    }

}
