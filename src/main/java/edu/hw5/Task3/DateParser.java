package edu.hw5.Task3;

import edu.hw5.Task3.HelpParsers.DefaultParser;
import edu.hw5.Task3.HelpParsers.Parser;
import edu.hw5.Task3.HelpParsers.SemiVerboseParser;
import edu.hw5.Task3.HelpParsers.SlashParser;
import edu.hw5.Task3.HelpParsers.VerboseParser;
import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    private DateParser() {

    }

    public static Optional<LocalDate> parse(String str) {
        Parser parser = link(new DefaultParser(), new SlashParser(),
            new VerboseParser(), new SemiVerboseParser());
        LocalDate result = parser.get(str);
        return Optional.ofNullable(result);
    }

    private static Parser link(Parser head, Parser... chain) {
        Parser current = head;
        for (Parser parser : chain) {
            current.setNext(parser);
            current = parser;
        }
        return head;
    }
}
