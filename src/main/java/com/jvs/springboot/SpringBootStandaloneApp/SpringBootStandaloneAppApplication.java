package com.jvs.springboot.SpringBootStandaloneApp;

import com.jvs.springboot.SpringBootStandaloneApp.com.jvs.springboot.service.DefaultHelloService;
import com.jvs.springboot.SpringBootStandaloneApp.com.jvs.springboot.service.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringBootStandaloneAppApplication implements CommandLineRunner {

    @Resource
    private HelloService helloService;

    @Bean
    public HelloService getHelloService(){
        return  new DefaultHelloService();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Spring Boot - Standalone App");
        getHelloService().hello();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStandaloneAppApplication.class, args);
    }
}
