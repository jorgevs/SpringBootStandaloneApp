package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamCreation {

    public static void main(String[] Args){

        //******************* Stream creation *******************

        //=================== Stream of Collection ===================
        //Stream can also be created of any type of Collection (Collection, List, Set)
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        //=================== Stream of Array ===================
        Stream<String> streamOfArray = Stream.of("a", "b", "c");

        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        //=================== Stream of Primitives ===================
        int[] numbers = {4, 5, 6, 7, 2, 4};
        IntStream streamNumbers = IntStream.of(numbers);

        //range(int startInclusive, int endExclusive)
        IntStream intStream2 = IntStream.range(1, 3);

        //rangeClosed(int startInclusive, int endInclusive)
        LongStream longStream2 = LongStream.rangeClosed(1, 3);

        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3);

        //=================== Stream of String ===================
        IntStream streamOfChars = "abc".chars();
        Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

        //=================== Stream of File ===================
        Path path = Paths.get("C:\\logs\\spring.log");
        try {
            Stream<String> streamOfStrings = Files.lines(path);
            Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //=================== Stream creation methods ===================
        // Stream.empty()
        Stream<String> streamEmpty = Stream.empty();

        // Stream.builder()
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();
        Stream<Integer> intStreamBuilder = Stream.<Integer>builder().add(1).add(7).add(11).build();
        Stream<Long> longStreamBuilder = Stream.<Long>builder().add(1L).add(7L).add(11L).build();

        // Stream.generate()
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

        IntSupplier intSupplier = () -> (int)(Math.random() * 10);
        IntStream intStream = IntStream.generate(intSupplier).limit(10);

        LongSupplier longSupplier = () -> (long)(Math.random() * 10);
        LongStream longStream = LongStream.generate(longSupplier).limit(10);

        // Stream.iterate()
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

    }

}
