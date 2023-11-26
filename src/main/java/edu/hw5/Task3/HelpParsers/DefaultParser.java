package edu.hw5.Task3.HelpParsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultParser extends Parser {

    @Override
    public LocalDate get(String str) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-(?<day>\\d{1,2})$");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        if (matcher.hasMatch()) {
            if (matcher.group("day").length() == 1) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                return LocalDate.parse(str, formatter);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(str, formatter);
            }
        }
        return (next == null) ? null : next.get(str);
    }

}
