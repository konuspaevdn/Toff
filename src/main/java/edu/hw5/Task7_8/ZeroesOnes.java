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
        Pattern pattern = Pattern.compile("((^0[01]*0$)|(^1[01]*1$))");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkLengthBetween1and3(String str) {  // Task7
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkOddLength(String str) {  // Task8
        Pattern pattern = Pattern.compile("^[01]([01][01])*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkFirstSAndLength(String str) {  // Task8
        Pattern pattern = Pattern.compile("((^0([01][01])*$)|(^1[01]([01][01])*$))");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkZeroesNumMultipleOf3(String str) {  // Task8
        Pattern pattern = Pattern.compile("^1*(01*01*01*)*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkForForbiddenStrings(String str) {  // Task8
        Pattern pattern = Pattern.compile("^((11)|(111))$");
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }

    public static Boolean checkOddSIsOne(String str) {  // Task8
        Pattern pattern = Pattern.compile("^(1[01])*1?$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkTwoZeroesOneOne(String str) {  // Task8
        Pattern pattern = Pattern.compile("((^00+1?$)|(^100+$)|(^0+10+$))");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static Boolean checkForConsequentialOnes(String str) {  // Task8
        Pattern pattern = Pattern.compile("[01]*11");
        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }
}
