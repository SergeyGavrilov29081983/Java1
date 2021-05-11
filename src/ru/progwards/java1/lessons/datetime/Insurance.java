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

        } else {

        }

    } /*- установить дату-время начала действия страховки
    SHORT соответствует ISO_LOCAL_DATE
    LONG  - ISO_LOCAL_DATE_TIME
    FULL - ISO_ZONED_DATE_TIME
    Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию.*/


/*
1.3 Реализовать методы, устанавливающие продолжительность действия страховки:
*/

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

    }//- установить продолжительность действия страховки
    //SHORT - целое число миллисекунд (тип long)
    //LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает, что продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
           // FULL - стандартный формат Duration, который получается через toString()

}
