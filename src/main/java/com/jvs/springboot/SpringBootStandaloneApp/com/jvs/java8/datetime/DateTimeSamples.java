package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeSamples {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println(instant.toString());

        System.out.println(Instant.now().atZone(ZoneId.of("America/Montreal")));
        System.out.println(Instant.now().atZone(ZoneId.of("UTC")));
        System.out.println(Instant.now().toString());

        System.out.println(OffsetDateTime.ofInstant(instant, ZoneId.of("America/New_York")));
        System.out.println(Instant.now().atOffset(ZoneOffset.UTC));

        ZonedDateTime departure = ZonedDateTime.ofInstant(instant, ZoneId.of("America/New_York"));
        System.out.println(departure.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
        System.out.println(departure.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        ZonedDateTime departureUTC = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
        System.out.println(departureUTC.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
