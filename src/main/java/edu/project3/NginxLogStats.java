package edu.project3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NginxLogStats {
    public void printStats(Map<String, String> args) throws IOException {
        Pattern pattern = Pattern.compile("^https?:");
        Matcher matcher = pattern.matcher(args.get("path"));
        if (!matcher.find()) {
            try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(Path.of(args.get("dir")), args.get("path"))) {
                for (Path path : stream) {

                }
            }
        } else {

        }
    }
}
