package com.jvs.springboot.SpringBootStandaloneApp.com.jvs.java8.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UTCZonedDateFormat {

    public static void main(String[] args) {

        ////////////////////////////// SAMPLE WITH A TEAMSITE DATE ///////////////////////////////////////
        String dateFromTeamsiteStr = "2011-09-04 06:23:46";
        String TEAMSITE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


        // String to java.util.Date
        Date dateFromString = null;
        try {
            SimpleDateFormat tsSimpleDateFormat = new SimpleDateFormat(TEAMSITE_DATE_FORMAT);
            dateFromString = tsSimpleDateFormat.parse(dateFromTeamsiteStr);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        System.out.println("dateFromString: " + dateFromString);

        // String to LocalDateTime
        DateTimeFormatter tsDateTimeFormatter = DateTimeFormatter.ofPattern(TEAMSITE_DATE_FORMAT);
        LocalDateTime localDateTimeFromString = LocalDateTime.from(tsDateTimeFormatter.parse(dateFromTeamsiteStr));
        System.out.println("localDateTimeFromString: " + localDateTimeFromString);


        // java.util.Date to LocalDateTime
        LocalDateTime localDateTimeFromDate = dateFromString.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTimeFromDate: " + localDateTimeFromDate);

        // LocalDateTime to java.util.Date
        Date dateFromLocalDateTime = Date.from(localDateTimeFromDate.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("dateFromLocalDateTime: " + dateFromLocalDateTime);


        ZoneId zoneId_NY = ZoneId.of("America/New_York");
        ZoneId zoneId_UTC = ZoneId.of("UTC");

        // localDateTimeFromString to ZonedDateTime(NY)
        ZonedDateTime zonedDT_NY = localDateTimeFromString.atZone(zoneId_NY);
        System.out.println("zonedDT_NY: " + zonedDT_NY);
        System.out.println("zonedDT_NY (formatted): " + zonedDT_NY.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        // localDateTimeFromString to ZonedDateTime(UTC)
        ZonedDateTime zonedDT_UTC = localDateTimeFromString.atZone(zoneId_UTC);
        System.out.println("zonedDT_UTC: " + zonedDT_UTC);
        System.out.println("zonedDT_UTC (formatted): " + zonedDT_UTC.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));


        // ZonedDateTime(NY) to ZonedDateTime(UTC)
        ZonedDateTime zonedDT_UTC1 = zonedDT_NY.withZoneSameInstant(zoneId_UTC);
        System.out.println("zonedDT_UTC1: " + zonedDT_UTC1);
        System.out.println("zonedDT_UTC1 (formatted): " + zonedDT_UTC1.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    }

}
