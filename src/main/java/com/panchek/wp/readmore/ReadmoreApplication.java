package com.panchek.wp.readmore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        ReadmoreApplication.class,
        Jsr310Converters.class
})
public class ReadmoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadmoreApplication.class, args);
    }

}
