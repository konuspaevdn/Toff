package edu.project3;

import java.util.HashMap;
import java.util.Map;

public class CommandLineParser {
    private CommandLineParser() {

    }

    public static Map<String, String> parseArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (arg.equals("--path")) {
                arguments.put("path", args[i + 1]);
            }
            if (arg.equals("--from")) {
                arguments.put("from", args[i + 1]);
            }
            if (arg.equals("--to")) {
                arguments.put("to", args[i + 1]);
            }
            if (arg.equals("--format")) {
                arguments.put("format", args[i + 1]);
            }
        }
        return arguments;
    }
}
