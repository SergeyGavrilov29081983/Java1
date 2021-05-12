package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Insurance {

    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration;

    public Insurance(ZonedDateTime start) {
        this.start = start;

    }

    public Insurance(String strStart, FormatStyle style) {
        ZoneId zone = ZoneId.of("Europe/Moscow");
        DateTimeFormatter dateTimeFormatter;
        if (style.equals(FormatStyle.SHORT)) {
            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate localDate = LocalDate.parse(strStart, dateTimeFormatter);
            LocalTime localTime = LocalTime.of(0, 0 ,0, 0);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            start = ZonedDateTime.of(localDateTime, zone);

        } else if (style.equals(FormatStyle.LONG)) {
            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDate localDate = LocalDate.parse(strStart, dateTimeFormatter);
            LocalTime localTime = LocalTime.of(0, 0 ,0, 0);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            start = ZonedDateTime.of(localDateTime, zone);

        } else if (style.equals(FormatStyle.FULL)) {
            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDate localDate = LocalDate.parse(strStart, dateTimeFormatter);
            LocalTime localTime = LocalTime.of(0, 0 ,0, 0);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            start = ZonedDateTime.of(localDateTime, zone);

        }

    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours) {
        duration = Duration.ofHours(hours).plusDays(days).plusDays(months* 30);
    }

    public void setDuration(String strDuration, FormatStyle style) {
        switch (style) {
            case SHORT:
                long millis = Long.parseLong(strDuration);
                duration = Duration.ofMillis(millis);
                break;
            case LONG:
                int years = Integer.parseInt(strDuration.substring(0,4));
                int months = Integer.parseInt(strDuration.substring(5,7));
                int days = Integer.parseInt(strDuration.substring(8,10));
                int hours = Integer.parseInt(strDuration.substring(11,13));
                int minutes = Integer.parseInt(strDuration.substring(14,16));
                int seconds = Integer.parseInt(strDuration.substring(17,19));
                duration = Duration.ofSeconds(seconds).plusMinutes(minutes).plusHours(hours);
                duration = duration.plusDays(days).plusDays(months*30).plusDays(years*365);
                break;
            case FULL:
                duration = Duration.parse(strDuration);
                break;
            default:
                System.out.println("Формат задан не верно");
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

}
