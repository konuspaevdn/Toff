package edu.hw3.Task1;

import java.util.ArrayList;
import java.util.List;

public class Atbash {
    private static final int ALPHABET_SIZE = 26;
    private static final List<Character> LOWER_CASE_ALPHABET = new ArrayList<>(ALPHABET_SIZE);
    private static final List<Character> UPPER_CASE_ALPHABET = new ArrayList<>(ALPHABET_SIZE);

    static {
        for (char c = 'a'; c <= 'z'; ++c) {
            LOWER_CASE_ALPHABET.add(c);
        }
        for (char c = 'A'; c <= 'Z'; ++c) {
            UPPER_CASE_ALPHABET.add(c);
        }
    }

    private Atbash() {

    }

    public static String cipher(String l) {
        char[] str = l.toCharArray();
        StringBuilder ciphered = new StringBuilder(l.length());
        for (Character c : str) {
            if (Character.isLetter(c)) {
                char letter;
                if (Character.isLowerCase(c)) {
                    letter = LOWER_CASE_ALPHABET.get(ALPHABET_SIZE - LOWER_CASE_ALPHABET.indexOf(c) - 1);
                } else {
                    letter = UPPER_CASE_ALPHABET.get(ALPHABET_SIZE - UPPER_CASE_ALPHABET.indexOf(c) - 1);
                }
                ciphered.append(letter);
            } else {
                ciphered.append(c);
            }
        }
        return ciphered.toString();
    }
}
