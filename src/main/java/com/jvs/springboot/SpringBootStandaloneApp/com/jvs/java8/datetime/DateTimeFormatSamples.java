package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;


public class DateTimeFormatSamples {

    public static void main(String[] args) {

        // An Instant represents a moment on the timeline in UTC with a resolution of up to nanoseconds.
        Instant instant = Instant.now();
        System.out.println("Instant(UTC):" + instant.toString());


        // The ZonedDateTime class, combines the LocalDateTime class with the ZoneId class.
        // It is used to represent a full date (year, month, day) and time (hour, minute, second, nanosecond)
        // with a time zone (region/city, such as Europe/Paris).
        ZoneId zoneId_Mexico = ZoneId.of("America/Mexico_City");
        ZoneId zoneId_NY = ZoneId.of("America/New_York");
        ZoneId zoneId_UTC = ZoneId.of("UTC");

        ZonedDateTime zonedDateTime_Mexico = ZonedDateTime.ofInstant(instant, zoneId_Mexico);
        System.out.println("zonedDateTime_Mexico: " + zonedDateTime_Mexico);
        System.out.println("zonedDateTime_Mexico (formatted):" + zonedDateTime_Mexico.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        ZonedDateTime zonedDateTime_NY = ZonedDateTime.ofInstant(instant, zoneId_NY);
        System.out.println("zonedDateTime_NY: " + zonedDateTime_NY);
        System.out.println("zonedDateTime_NY (formatted):" + zonedDateTime_NY.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        ZonedDateTime zonedDateTime_UTC = ZonedDateTime.ofInstant(instant, zoneId_UTC);
        System.out.println("zonedDateTime_UTC: " + zonedDateTime_UTC);
        System.out.println("zonedDateTime_UTC (formatted):" + zonedDateTime_UTC.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));


        // print all available zoneIds
        System.out.println(ZoneId.getAvailableZoneIds());


        // The OffsetDateTime class, combines the LocalDateTime class with the ZoneOffset class.
        // It is used to represent a full date (year, month, day) and time (hour, minute, second, nanosecond)
        // with an offset from Greenwich/UTC time (+/-hours:minutes, such as +06:00 or -08:00).
        ZoneOffset zoneOffset_Mexico = ZoneOffset.from(zonedDateTime_Mexico);
        ZoneOffset zoneOffset_NY = ZoneOffset.from(zonedDateTime_NY);
        ZoneOffset zoneOffset_UTC = ZoneOffset.UTC;

        OffsetDateTime offsetDateTime_Mexico = OffsetDateTime.ofInstant(instant, zoneOffset_Mexico);
        System.out.println("offsetDateTime_Mexico: " + offsetDateTime_Mexico);
        System.out.println("offsetDateTime_Mexico (formatted): " + offsetDateTime_Mexico.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        OffsetDateTime offsetDateTime_NY = OffsetDateTime.ofInstant(instant, zoneOffset_NY);
        System.out.println("offsetDateTime_NY: " + offsetDateTime_NY);
        System.out.println("offsetDateTime_NY (formatted): " + offsetDateTime_NY.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        OffsetDateTime offsetDateTime_UTC = OffsetDateTime.ofInstant(instant, zoneOffset_UTC);
        System.out.println("offsetDateTime_UTC: " + offsetDateTime_UTC);
        System.out.println("offsetDateTime_UTC (formatted): " + offsetDateTime_UTC.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    }

}
