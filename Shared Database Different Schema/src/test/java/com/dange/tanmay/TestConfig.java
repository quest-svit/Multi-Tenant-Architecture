package com.dange.tanmay;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
public class TestConfig {

    static {
        System.setProperty("spring.config.location", "classpath:test.properties");
    }

}