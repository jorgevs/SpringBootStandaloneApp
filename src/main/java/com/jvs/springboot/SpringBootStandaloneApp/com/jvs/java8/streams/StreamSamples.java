package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.streams;


import java.util.IntSummaryStatistics;
import java.util.Objects;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSamples {

    public static void main(String[] args) {

        System.out.println("Find the maximum number, filter null values:");
        Stream<Integer> streamOfIntegers = Stream.of(1, 22, 11, null, 9, 3);
        streamOfIntegers
                .filter(Objects::nonNull)
                .max(Integer::compareTo)
                .ifPresent(System.out::println);


        int[] numbers = {4, 5, 6, 7, 2, 4, -2, -9, 13};

        System.out.println("Get the minimum number. Only positive numbers:");
        IntStream.of(numbers)
                .filter(x -> x > 0).min()
                .ifPresent(x -> System.out.println(x));

        System.out.println("Single call for all statistics - SummaryStatistics:");
        System.out.println(IntStream.of(numbers).summaryStatistics());

        System.out.println("Individual statistics:");
        IntSummaryStatistics statistics = IntStream.of(numbers).summaryStatistics();
        System.out.println("count: " + statistics.getCount());
        System.out.println("sum: " + statistics.getSum());
        System.out.println("min: " + statistics.getMin());
        System.out.println("average: " + statistics.getAverage());
        System.out.println("max: " + statistics.getMax());

        // Find 3 distinct smallest numbers
        System.out.println("Find 3 distinct smallest numbers:");
        IntStream.of(numbers)
                .distinct()
                .sorted()
                .limit(3)
                .forEach(System.out::println);


        IntSupplier intSupplier = () -> (int) (Math.random() * 10);
        IntStream intStream = IntStream.generate(intSupplier).limit(10);

        System.out.println("Print odd numbers:");
        intStream
                .filter(x -> x%2 == 0)
                .forEach(System.out::println);



        Supplier<Stream<String>> stringSupplier = () -> Stream.of("Didier", "Silvia", "Beto", "Miguel", "Jorge");

        stringSupplier.get()
                .sorted()
                .forEach(System.out::println);

        stringSupplier.get()
                .findFirst()
                .ifPresent(System.out::println);

        


    }

}
