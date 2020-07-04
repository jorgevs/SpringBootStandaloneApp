package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.lambdas;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.*;

public class FunctionalInterfaceSamples {

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

        // Static method reference using ::
        Consumer<String> print = System.out::println;
        print.accept("Coming to you directly from a Lambda...");

        //=================================================================================
        // Supplier example
        Supplier<String> s = () -> "Java is fun";
        System.out.println(s.get());

        //=================================================================================
        // Function example
        Function<Integer, String> converter = (num) -> Integer.toString(num);
        System.out.println("Length of 26: " + converter.apply(26).length());

        // Static method reference using ::
        Function<String, BigInteger> newBigInt = BigInteger::new;
        System.out.println("expected value: 123456789, actual value: " + newBigInt.apply("123456789"));

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

        UnaryOperator<String> makeGreeting = "Hello"::concat;
        System.out.println(makeGreeting.apply("Peggy"));

        //=================================================================================
        IntFunction<String> intToString = num -> Integer.toString(num);
        System.out.println("expected value: 3, actual value: " + intToString.apply(123).length());

        // Static method reference using ::
        IntFunction<String> intToString2 = Integer::toString;
        System.out.println("expected value: 4, actual value: " + intToString2.apply(4567).length());

        //=================================================================================
        // Example of custom functional interface
        GreetingFuntion greetingFuntion = (message) -> System.out.println("Java programming " + message);
        greetingFuntion.sayMessage("rocks with Lambda!");

        Calculate addition = (a, b) -> a + b;
        Calculate difference = (a, b) -> Math.abs(a - b);
        Calculate divide = (a, b) -> (b != 0 ? a / b : 0);
        Calculate multiply = (a, b) -> a * b;

        System.out.println(addition.calc(3, 2));
        System.out.println(difference.calc(5, 10));
        System.out.println(divide.calc(12, 3));
        System.out.println(multiply.calc(3, 5));
    }

    @FunctionalInterface
    interface GreetingFuntion {
        void sayMessage(String message);
    }

    @FunctionalInterface
    interface Calculate {
        int calc(int x, int y);
    }
}
