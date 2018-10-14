package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.springboot.service;

public class DefaultHelloService implements HelloService {
    @Override
    public void hello() {
        System.out.println("Hello world!... from Hello Service");
    }
}
