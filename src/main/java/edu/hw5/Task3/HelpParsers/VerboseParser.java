package edu.hw5.Task3.HelpParsers;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerboseParser extends Parser {

    private enum Day {
        TOMORROW, TODAY, YESTERDAY
    }

    @Override
    public LocalDate get(String str) {
        Pattern pattern = Pattern.compile("^(tomorrow|today|yesterday)$");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        if (matcher.hasMatch()) {
            switch (Day.valueOf(matcher.group().toUpperCase())) {
                case TOMORROW -> {
                    return LocalDate.now().plusDays(1);
                }
                case TODAY -> {
                    return LocalDate.now();
                }
                case YESTERDAY -> {
                    return LocalDate.now().minusDays(1);
                }
                default -> {

                }
            }
        }
        return (next == null) ? null : next.get(str);

    }

}
