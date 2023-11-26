package edu.hw5.Task3.HelpParsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlashParser extends Parser {

    @SuppressWarnings("MagicNumber")
    @Override
    public LocalDate get(String str) {
        Pattern pattern = Pattern.compile("^(?<month>\\d{1,2})/(?<day>\\d{1,2})/(?<year>(\\d\\d){1,2})$");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        if (matcher.hasMatch()) {
            DateTimeFormatter formatter = null;
            switch (matcher.group("day").length() + matcher.group("month").length() * 10
                + matcher.group("year").length() * 50) {
                case 111 -> {
                    formatter = DateTimeFormatter.ofPattern("M/d/yy");
                }
                case 112 -> {
                    formatter = DateTimeFormatter.ofPattern("M/dd/yy");
                }
                case 121 -> {
                    formatter = DateTimeFormatter.ofPattern("MM/d/yy");
                }
                case 122 -> {
                    formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
                }
                case 211 -> {
                    formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                }
                case 212 -> {
                    formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
                }
                case 221 -> {
                    formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
                }
                case 222 -> {
                    formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                }
                default -> {

                }
            }
            return LocalDate.parse(str, formatter);
        }
        return (next == null) ? null : next.get(str);
    }
}
