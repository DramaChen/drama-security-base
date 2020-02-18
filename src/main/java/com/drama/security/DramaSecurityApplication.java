package com.drama.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.drama.security")
public class DramaSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(DramaSecurityApplication.class,args);
    }
}
