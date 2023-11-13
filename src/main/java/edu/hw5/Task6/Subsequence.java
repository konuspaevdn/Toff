package edu.hw5.Task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subsequence {
    private Subsequence() {

    }

    public static Boolean checkIfSubsequence(String sub, String sup) {
        StringBuilder regex = new StringBuilder();
        for (var c : sub.toCharArray()) {
            regex.append(c);
            regex.append(".*");
        }
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(sup);
        return matcher.find();
    }

}
