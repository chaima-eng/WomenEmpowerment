package com.example.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.annotation.Resource;

@EnableSwagger2
@SpringBootApplication
@EnableScheduling
public class BackendApplication  {


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }



}
