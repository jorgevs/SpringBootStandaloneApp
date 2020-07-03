package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.lambdas;

import java.util.Arrays;
import java.util.function.*;

public class FunctionalInterfaceExample1 {

    public static void main(String[] args) {

        //=================================================================================
        // Anonymous inner class:  Runnable
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!");
            }
        };

        // Lambda version of Runnable (no arguments)
        Runnable r2 = () -> System.out.println("Hello world!");

        // Start both threads
        r1.run();
        r2.run();

        //=================================================================================
        // Using an existing functional interface Bifunction
        BiFunction<String, String, String> concat = (a, b) -> a + b;
        System.out.println(concat.apply("Today ", "is a great day"));

        //=================================================================================
        // Example of the Consumer functional interface
        Consumer<String> hello = (name) -> System.out.println("Hello, " + name);
        for (String name : Arrays.asList("Duke", "Mickey", "Minnie")) {
            hello.accept(name);
        }

        // Consumer example uses accept method
        Consumer<String> consumer = (s) -> System.out.println(s.toLowerCase());
        consumer.accept("MAYUSCULAS");

        //=================================================================================
        // Supplier example
        Supplier<String> s = () -> "Java is fun";
        System.out.println(s.get());

        //=================================================================================
        // Function example
        Function<Integer, String> converter = (num) -> Integer.toString(num);
        System.out.println("Length of 26: " + converter.apply(26).length());

        //=================================================================================
        // Using the test method of Predicate
        Predicate<String> stringLen = (x) -> x.length() < 10;
        System.out.println(stringLen.test("Apple") + " - Apples is less than 10");

        //=================================================================================
        // Binary Operator example
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("add 10 + 25 = " + add.apply(10, 25));

        //=================================================================================
        // Unary Operator example
        UnaryOperator<String> str = (msg) -> msg.toUpperCase();
        System.out.println(str.apply("This is my message in upper case."));

        //=================================================================================
        // Example of custom functional interface
        GreetingFuntion greetingFuntion = (message) -> System.out.println("Java programming " + message);
        greetingFuntion.sayMessage("rocks with Lambda!");
        
    }

    @FunctionalInterface
    interface GreetingFuntion {
        void sayMessage(String message);
    }
}
