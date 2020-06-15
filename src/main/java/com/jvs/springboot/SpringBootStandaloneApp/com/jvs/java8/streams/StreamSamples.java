package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.streams;


import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSamples {

    public static void main(String[] args) {

        //=================================================================================
        System.out.println("Find the maximum number, filter null values:");
        Stream<Integer> streamOfIntegers = Stream.of(1, 22, 11, null, 9, 3);

        streamOfIntegers
                .filter(Objects::nonNull)
                .max(Integer::compareTo)
                .ifPresent(System.out::println);

        //=================================================================================
        int[] numbers = {4, 5, 6, 7, 2, 4, -2, -9, 13};

        System.out.println("Get the minimum number. Only positive numbers:");
        IntStream.of(numbers)
                .filter(x -> x > 0)
                .min()
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

        //=================================================================================
        IntSupplier intSupplier = () -> (int) (Math.random() * 10);
        IntStream intStream = IntStream.generate(intSupplier).limit(10);

        System.out.println("Print odd numbers:");
        intStream
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);

        //=================================================================================
        Supplier<Stream<String>> stringSupplier = () -> Stream.of("Didier", "Silvia", "Beto", "Miguel", "Jorge");

        System.out.println("Sorting strings:");
        stringSupplier.get()
                .sorted()
                .forEach(System.out::println);

        System.out.println("Find first string:");
        stringSupplier.get()
                .findFirst()
                .ifPresent(System.out::println);

        //=================================================================================
        System.out.println("Average:");
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0

        //=================================================================================
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        System.out.println("Filter, toUpperCase and sort strings:");
        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);  //C1, C2

        //=================================================================================
        System.out.println("Substring, convert to Integer, get the maximum value:");
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3

        //=================================================================================
        System.out.println("Convert int to Object:");
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        //=================================================================================
        System.out.println("Print the execution and calling order:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //=================================================================================
        System.out.println("Order the chain to optimize the performance:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //=================================================================================
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        System.out.println("Filter object which name starts with 'P' and create a list:");
        List<Person> filtered =
                persons.stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());

        //Need a set instead of list - just use Collectors.toSet()
        System.out.println(filtered);    // [Peter, Pamela]

        System.out.println("Group all persons by age:");
        Map<Integer, List<Person>> personsByAge =
                persons.stream()
                        .collect(Collectors.groupingBy(p -> p.age));

        personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        System.out.println("Determine the average age of all persons:");
        Double averageAge =
                persons.stream()
                        .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);     // 19.0

        System.out.println("Determine min, max and arithmetic average age of the persons as well as the sum and count:");
        IntSummaryStatistics ageSummary =
                persons.stream()
                        .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);
        // IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}

        System.out.println("Join all persons elder than 18 into a single string:");
        String phrase =
                persons.stream()
                        .filter(p -> p.age >= 18)
                        .map(p -> p.name)
                        .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);
        // In Germany Max and Peter and Pamela are of legal age.

        System.out.println("Transform the stream elements into a map:");
        // We have to specify how both the keys and the values should be mapped.
        // Keep in mind that the mapped keys must be unique, otherwise an
        // IllegalStateException is thrown
        Map<Integer, String> map =
                persons.stream()
                        .collect(Collectors.toMap(
                                p -> p.age,
                                p -> p.name,
                                (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
        // {18=Max, 23=Peter;Pamela, 12=David}

        System.out.println("Transform all persons into a single string consisting of all names in upper letters separated by the | pipe character:");
        // In order to achieve this we create a new collector via Collector.of().
        // We have to pass the four ingredients of a collector: a supplier, an
        // accumulator, a combiner and a finisher.
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        // Since strings in Java are immutable, we need a helper class like StringJoiner
        // to let the collector construct our string. The supplier initially constructs
        // such a StringJoiner with the appropriate delimiter. The accumulator is used to
        // add each persons upper-cased name to the StringJoiner. The combiner knows how
        // to merge two StringJoiners into one. In the last step the finisher constructs
        // the desired String from the StringJoiner.
        String names =
                persons.stream()
                        .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
        //=================================================================================

        //=================================================================================

        //=================================================================================

    }

}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}