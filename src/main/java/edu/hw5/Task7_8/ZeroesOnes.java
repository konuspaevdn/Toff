package edu.hw5.Task7_8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZeroesOnes {
    private ZeroesOnes() {

    }

    public static Boolean checkLengthAndThirdS(String str) {  // Task7
        Pattern pattern = Pattern.compile("^[01]{2}0");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkSameFirstAndLastS(String str) {  // Task7
        Pattern pattern = Pattern.compile("(^0[01]*0$)?(^1[01]*1$)");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkLengthBetween1and3(String str) {  // Task7
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
