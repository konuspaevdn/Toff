package edu.hw5.Task2;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Fridays {
    private static final int MONTHS = 12;
    private static final int THIRTEEN = 13;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private Fridays() {
    }

    public static List<String> get(int year) {
        if (0 > year) {
            throw new DateTimeException("Only AD");
        }
        List<LocalDate> thirteens = new ArrayList<>(MONTHS);
        for (int m = 1; m <= MONTHS; ++m) {
            thirteens.add(LocalDate.of(year, m, THIRTEEN));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return thirteens.stream().filter(it -> it.getDayOfWeek() == DayOfWeek.FRIDAY)
            .map(it -> it.format(formatter)).toList();
    }

    public static String next(String d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(d, formatter);
        if (date.getDayOfMonth() >= THIRTEEN) {
            if (date.getMonth() == Month.DECEMBER) {
                date = LocalDate.of(date.getYear() + 1, 1, THIRTEEN);
            } else {
                date = LocalDate.of(date.getYear(), date.getMonthValue() + 1, THIRTEEN);
            }
        } else {
            date = LocalDate.of(date.getYear(), date.getMonth(), THIRTEEN);
        }
        while (date.getDayOfWeek() != DayOfWeek.FRIDAY) {
            date = date.plusMonths(1);
        }
        return date.format(formatter);
    }
}
