package com.boatcharter;

import com.boatcharter.boatImage.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties ({FileStorageProperties.class})
public class BoatcharterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoatcharterApplication.class, args);
    }

}
