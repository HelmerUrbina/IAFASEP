package com.iafasep;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ComponentScan
@EnableAutoConfiguration
@Configuration
@SpringBootApplication(scanBasePackages = {"com.iafasep"})
public class IafasepApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(IafasepApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IafasepApplication.class);
    }

}
