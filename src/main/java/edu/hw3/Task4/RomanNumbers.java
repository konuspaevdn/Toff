package edu.hw3.Task4;

import java.util.HashMap;

@SuppressWarnings("MagicNumber")
public class RomanNumbers {
    private static final HashMap<Integer, String> ONES;
    private static final HashMap<Integer, String> TENS;
    private static final HashMap<Integer, String> HUNDREDS;


    static {
        ONES = new HashMap<>();
        ONES.put(0, "");
        ONES.put(1, "I");
        ONES.put(2, "II");
        ONES.put(3, "III");
        ONES.put(4, "IV");
        ONES.put(5, "V");
        ONES.put(6, "VI");
        ONES.put(7, "VII");
        ONES.put(8, "VIII");
        ONES.put(9, "IX");

        TENS = new HashMap<>();
        TENS.put(0, "");
        TENS.put(1, "X");
        TENS.put(2, "XX");
        TENS.put(3, "XXX");
        TENS.put(4, "XL");
        TENS.put(5, "L");
        TENS.put(6, "LX");
        TENS.put(7, "LXX");
        TENS.put(8, "LXXX");
        TENS.put(9, "XC");

        HUNDREDS = new HashMap<>();
        HUNDREDS.put(0, "");
        HUNDREDS.put(1, "C");
        HUNDREDS.put(2, "CC");
        HUNDREDS.put(3, "CCC");
        HUNDREDS.put(4, "CD");
        HUNDREDS.put(5, "D");
        HUNDREDS.put(6, "DC");
        HUNDREDS.put(7, "DCC");
        HUNDREDS.put(8, "DCCC");
        HUNDREDS.put(9, "CM");

    }

    private RomanNumbers() {

    }

    public static String convertToRoman(int a) {
        int num = a;
        StringBuilder romanNum = new StringBuilder();
        romanNum.append("M".repeat(num / 1000));
        num %= 1000;
        romanNum.append(HUNDREDS.get(num / 100));
        num %= 100;
        romanNum.append(TENS.get(num / 10));
        num %= 10;
        romanNum.append(ONES.get(num));
        return romanNum.toString();
    }
}
