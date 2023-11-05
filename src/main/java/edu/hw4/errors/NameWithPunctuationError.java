package edu.hw4.errors;

import edu.hw4.Animal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameWithPunctuationError extends ValidationError {
    public Boolean check(Animal animal) {
        Pattern pattern = Pattern.compile("\\p{Punct}");
        Matcher m = pattern.matcher(animal.name());
        if (m.find()) {
            setMsg("Punctuation mark in name");
            setFields("name");
            return true;
        }
        return false;
    }
}
