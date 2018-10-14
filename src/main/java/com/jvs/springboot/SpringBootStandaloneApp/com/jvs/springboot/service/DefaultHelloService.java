package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultHelloService implements HelloService {

    @Override
    public void hello() {
        System.out.println("Hello world!... from Hello Service");
    }

}
