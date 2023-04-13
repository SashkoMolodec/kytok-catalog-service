package com.kytokvinily.vinyls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class VinylsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VinylsServiceApplication.class, args);
    }

}
