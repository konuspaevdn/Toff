package edu.hw5.Task3.HelpParsers;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SemiVerboseParser extends Parser {

    @Override
    public LocalDate get(String str) {
        Pattern pattern = Pattern.compile("^(?<number>\\d+)\\s+days?\\s+ago$");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        if (matcher.hasMatch()) {
            return LocalDate.now().minusDays(Integer.parseInt(matcher.group("number")));
        }
        return (next == null) ? null : next.get(str);
    }

}
