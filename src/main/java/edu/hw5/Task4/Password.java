package edu.hw5.Task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    private Password() {

    }

    public static Boolean check(String str) {
        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
