package edu.hw5.Task5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoPlate {
    private AutoPlate() {

    }

    public static Boolean check(String str) {
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

}
