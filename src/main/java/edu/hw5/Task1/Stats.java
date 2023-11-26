package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stats {
    private Stats() {

    }

    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;


    public static String averageDuration(List<String> sessions) {
        Function<String, Duration> parser = it -> {
            Pattern pattern = Pattern.compile(
                "^(?<start>\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2})\\s*-"
                    +
                    "\\s*(?<end>\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2})$");
            Matcher matcher = pattern.matcher(it);
            if (matcher.groupCount() != 2) {
                throw new RuntimeException("String " + it + "doesn't match the time format");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
            matcher.find();
            LocalDateTime start = LocalDateTime.parse(matcher.group("start"), formatter);
            LocalDateTime end = LocalDateTime.parse(matcher.group("end"), formatter);
            return Duration.between(start, end).abs();
        };

        Duration average =  sessions.stream().map(parser).reduce(Duration.ZERO, Duration::plus)
            .dividedBy(sessions.size());
        return average.toDays() + "д "
            +
            (average.toHours() - average.toDays() * HOURS_IN_DAY) + "ч "
            +
            (average.toMinutes() - average.toHours() * MINUTES_IN_HOUR) + "м";
    }
}
