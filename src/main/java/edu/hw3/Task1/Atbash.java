package edu.hw3.Task1;

import java.util.ArrayList;

public class Atbash {
    private static final int ALPHABET_SIZE = 26;
    private static ArrayList<Character> lowerCaseAlphabet = new ArrayList<>(ALPHABET_SIZE);
    private static ArrayList<Character> upperCaseAlphabet = new ArrayList<>(ALPHABET_SIZE);

    static {
        for (char c = 'a'; c <= 'z'; ++c) {
            lowerCaseAlphabet.add(c);
        }
        for (char c = 'A'; c <= 'Z'; ++c) {
            upperCaseAlphabet.add(c);
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
                    letter = lowerCaseAlphabet.get(ALPHABET_SIZE - lowerCaseAlphabet.indexOf(c) - 1);
                } else {
                    letter = upperCaseAlphabet.get(ALPHABET_SIZE - upperCaseAlphabet.indexOf(c) - 1);
                }
                ciphered.append(letter);
            } else {
                ciphered.append(c);
            }
        }
        return ciphered.toString();
    }
}
