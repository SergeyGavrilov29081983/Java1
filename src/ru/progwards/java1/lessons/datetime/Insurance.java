package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Insurance {

    private ZonedDateTime start;
    private Duration duration;
    public Insurance(ZonedDateTime start) {
        this.start = start;

    }

    public Insurance(String strStart, FormatStyle style) {
        ZoneId defaultZone = ZoneId.of("Europe/Moscow");
        DateTimeFormatter dtf;

        if (FormatStyle.SHORT == style) {
            dtf = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate ldate = LocalDate.parse(strStart, dtf);
            LocalTime ltime = LocalTime.of(0, 0, 0);
            LocalDateTime temp1 = LocalDateTime.of(ldate, ltime);
            this.start = temp1.atZone(defaultZone);

        } else if (FormatStyle.LONG == style) {
            dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime temp2 = LocalDateTime.parse(strStart, dtf);
            this.start = temp2.atZone(defaultZone);

        } else if (FormatStyle.FULL == style) {
            dtf = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            this.start = ZonedDateTime.parse(strStart, dtf);
        }


    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours) {
        duration = Duration.ofHours(hours).plusDays(days).plusDays(months * 30);
    }

    public void setDuration(String strDuration, FormatStyle style) {

        if (FormatStyle.SHORT == style) {
            long millis = Long.parseLong(strDuration);
            duration = Duration.ofMillis(millis);

        } else if (FormatStyle.LONG == style) {
            int years = Integer.parseInt(strDuration.substring(0, 4));
            int months = Integer.parseInt(strDuration.substring(5, 7));
            int days = Integer.parseInt(strDuration.substring(8, 10));
            int hours = Integer.parseInt(strDuration.substring(11, 13));
            int minutes = Integer.parseInt(strDuration.substring(14, 16));
            int seconds = Integer.parseInt(strDuration.substring(17, 19));
            duration = Duration.ofSeconds(seconds).plusMinutes(minutes).plusHours(hours);
            duration = duration.plusDays(days).plusDays(months * 30).plusDays(years * 365);

        } else if (FormatStyle.FULL == style) {
            duration = Duration.parse(strDuration);
        }
    }

    public boolean checkValid(ZonedDateTime dateTime) {
        if (dateTime.isBefore(start)) {
            return false;
        }
        if (duration == null || dateTime.isEqual(start)) {
            return true;
        }
        if (dateTime.isAfter(start)) {
            long seconds = duration.getSeconds();
            ZonedDateTime end = start.plusSeconds(seconds);
            return dateTime.isBefore(end);
        }
        return false;
    }

    @Override
    public String toString() {
        String validStr;
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("Europe/Moscow"));
        if (checkValid(zdt)) {
            validStr = " is valid";
        } else {
            validStr = " is not valid";
        }
        return "Insurance issued on " + start + validStr;
    }

    public enum FormatStyle {SHORT, LONG, FULL}

}
