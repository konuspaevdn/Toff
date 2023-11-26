package edu.hw4.errors;

import edu.hw4.Animal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameWithPunctuationError extends ValidationError {
    public NameWithPunctuationError() {
        msg = "Punctuation mark in name";
        fields = "name";
    }

    public Boolean check(Animal animal) {
        Pattern pattern = Pattern.compile("\\p{Punct}");
        Matcher m = pattern.matcher(animal.name());
        return m.find();
    }
}
