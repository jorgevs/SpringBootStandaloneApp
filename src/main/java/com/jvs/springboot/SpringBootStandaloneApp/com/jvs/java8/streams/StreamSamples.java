package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.streams;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSamples {
    public static final Logger LOGGER = LoggerFactory.getLogger(StreamSamples.class);

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

        //=================================================================================
        // SummaryStatistics
        System.out.println("Single call for all statistics - SummaryStatistics:");
        System.out.println(IntStream.of(numbers).summaryStatistics());

        System.out.println("Individual statistics:");
        IntSummaryStatistics statistics = IntStream.of(numbers).summaryStatistics();
        System.out.println("count: " + statistics.getCount());
        System.out.println("sum: " + statistics.getSum());
        System.out.println("min: " + statistics.getMin());
        System.out.println("average: " + statistics.getAverage());
        System.out.println("max: " + statistics.getMax());

        //=================================================================================
        // The peek() method exists mainly to support debugging, where you want to see the
        // elements as they flow past a certain point in a pipeline
        List<Integer> list2 = Arrays.asList(0, 2, 4, 6, 8, 10);

        System.out.println("Use of peek() method:");
        list2.stream().peek(System.out::println).count();

        //=================================================================================
        System.out.println("Use of peek() to alter the inner state of an element:");
        Stream<Person> userStream = Stream.of(new Person("Alice", 8), new Person("Bob", 9), new Person("Chuck", 11));
        userStream.peek(u -> u.setName(u.getName().toLowerCase()))
                .forEach(System.out::println);
        // Alternatively, we could have used map(), but peek() is more convenient since we don't want to replace the element.

        //=================================================================================
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

        //=================================================================================
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
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        list.stream()
                .skip(1)
                .map(element -> element.substring(0, 3))
                .sorted()
                .forEach(System.out::println);

        //=================================================================================
        // Sample to demonstrate the execution order:
        Optional<String> stream = list.stream()
                .filter(element -> {
                    LOGGER.info("filter() was called");
                    return element.contains("2");})
                .map(element -> {
                    LOGGER.info("map() was called");
                    return element.toUpperCase();})
                .findFirst();


        // ********************************************************************************
        // **************************** The reduce() Method *******************************
        // ********************************************************************************

        //=================================================================================
        // Reduction of a stream sample:
        OptionalInt reduced = IntStream.range(1, 4)
                .reduce((a, b) -> a + b);

        reduced.ifPresent(System.out::println); // 6

        //=================================================================================
        // Reduction of a stream sample:
        int reducedTwoParams = IntStream.range(1, 4)
                .reduce(10, (a, b) -> a + b);

        System.out.println("reducedTwoParams:" + reducedTwoParams); // 16

        //=================================================================================
        // To make a combiner work, a stream should be parallel:
        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    LOGGER.info("combiner was called");
                    return a + b;
                });

        LOGGER.info("reducedParallel: " + reducedParallel); // 36
        // As a result, they have (10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;).
        // Now combiner can merge these three results. It needs two iterations for that (12 + 13 = 25; 25 + 11 = 36).


        // ********************************************************************************
        // **************************** The collect() Method ******************************
        // ********************************************************************************

        List<Person> personList = Arrays.asList(new Person("Didier", 23),
                                                new Person("Jorge", 22),
                                                new Person("Juan", 13),
                                                new Person("Silvia", 12),
                                                new Person("David", 13));

        //=================================================================================
        // Converting a stream to the Collection (Collection, List or Set):
        List<String> collectorCollection = personList.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("collectorCollection: " + collectorCollection);  // [Didier, Jorge, Juan, Silvia, David]

        //=================================================================================
        System.out.println("Filter object which name starts with 'J' and create a list:");
        List<Person> filtered = personList.stream()
                .filter(p -> p.name.startsWith("J"))
                .collect(Collectors.toList());

        //Need a set instead of list - just use Collectors.toSet()
        System.out.println(filtered);    // [Jorge, Juan]
        //=================================================================================
        // Reducing to String:
        String listToString = personList.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println("listToString: " + listToString);    // [Didier, Jorge, Juan, Silvia, David]

        //=================================================================================
        System.out.println("Join all persons elder than 18 into a single string:");
        String phrase = personList.stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase); // In Germany Didier and Jorge are of legal age.

        // The joiner() method can have from one to three parameters (delimiter, prefix, suffix).
        // The handiest thing about using joiner() – developer doesn't need to check if the stream
        // reaches its end to apply the suffix and not to apply a delimiter. Collector will take care of that.

        //=================================================================================
        // Processing the average value of all numeric elements of the stream:
        System.out.println("Determine the average age of all persons:");
        double avgAge = personList.stream()
                .collect(Collectors.averagingInt(Person::getAge));

        System.out.println("avgAge: " + avgAge);    // 16.6

        //=================================================================================
        // Processing the sum of all numeric elements of the stream:
        int summingAges = personList.stream()
                .collect(Collectors.summingInt(Person::getAge));

        System.out.println("summingAges: " + summingAges);    // 83

        // Methods averagingXX(), summingXX() and summarizingXX() can work as with primitives (int, long, double)
        // as with their wrapper classes (Integer, Long, Double).

        // One more powerful feature of these methods is providing the mapping. So, developer doesn't need to use
        // an additional map() operation before the collect() method.

        //=================================================================================
        // Collecting statistical information about stream’s elements:
        System.out.println("Determine min, max and arithmetic average age of the persons as well as the sum and count:");
        IntSummaryStatistics stats = personList.stream()
                .collect(Collectors.summarizingInt(Person::getAge));

        System.out.println("stats: " + stats);  // IntSummaryStatistics{count=5, sum=83, min=12, average=16.600000, max=23}

        //=================================================================================
        // Grouping of stream’s elements according to the specified function:
        System.out.println("Group all persons by age:");
        Map<Integer, List<Person>> personsByAge = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));    // age 22: [Jorge]
                                                                                        // age 23: [Didier]
                                                                                        // age 12: [Silvia]
                                                                                        // age 13: [Juan, David]

        //=================================================================================
        // Dividing stream’s elements into groups according to some predicate:
        Map<Boolean, List<Person>> mapPartioned = personList.stream()
                .collect(Collectors.partitioningBy(element -> element.getAge() > 20));

        mapPartioned.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));    // age false: [Juan, Silvia, David]
                                                                                        // age true: [Didier, Jorge]
        //=================================================================================
        //Pushing the collector to perform additional transformation:
        Set<Person> unmodifiableSet = personList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        Collections::unmodifiableSet));

        // In this particular case, the collector has converted a stream to a Set and then created the
        // unmodifiable Set out of it.

        //=================================================================================
        System.out.println("Transform the stream elements into a map:");
        // We have to specify how both the keys and the values should be mapped.
        // Keep in mind that the mapped keys must be unique, otherwise an
        // IllegalStateException is thrown
        Map<Integer, String> map = personList.stream()
                .collect(
                        Collectors.toMap(
                            p -> p.age,
                            p -> p.name,
                            (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);    // {22=Jorge, 23=Didier, 12=Silvia, 13=Juan;David}

        //=================================================================================
        // Custom collector:
        System.out.println("Transform all persons into a single string consisting of all names in upper letters separated by the | pipe character:");
        // In order to achieve this we create a new collector via Collector.of().
        // We have to pass the four ingredients of a collector: a supplier, an
        // accumulator, a combiner and a finisher.
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),  // supplier
                        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        // Since strings in Java are immutable, we need a helper class like StringJoiner
        // to let the collector construct our string. The supplier initially constructs
        // such a StringJoiner with the appropriate delimiter. The accumulator is used to
        // add each persons upper-cased name to the StringJoiner. The combiner knows how
        // to merge two StringJoiners into one. In the last step the finisher constructs
        // the desired String from the StringJoiner.

        String names = personList.stream()
                .collect(personNameCollector);

        System.out.println(names);  // DIDIER | JORGE | JUAN | SILVIA | DAVID

        //=================================================================================
        Collector<Person, ?, LinkedList<Person>> toLinkedListCollector =
                Collector.of(
                        LinkedList::new,
                        LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        });

        LinkedList<Person> linkedListOfPersons = personList.stream()
                .collect(toLinkedListCollector);

        System.out.println("linkedListOfPersons: " + linkedListOfPersons);  // [Didier, Jorge, Juan, Silvia, David]

        //In this example, an instance of the Collector got reduced to the LinkedList<Person>.

        //=================================================================================
        System.out.println("Paginating items from a Stream:");
        System.out.println(getEvenNumbers(5, 5));
        System.out.println(getEvenNumbers(10, 5));
        System.out.println(getEvenNumbers(15, 5));
    }


    private static List<Integer> getEvenNumbers(int offset, int limit) {
        return Stream.iterate(0, i -> i + 1)
                .filter(i -> i % 2 == 0)
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}