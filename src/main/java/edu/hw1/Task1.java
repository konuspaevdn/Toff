package edu.hw1;


public class Task1 {
    final static private int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static int convertTime(String str) {
        int seconds;
        try {
            seconds = Integer.parseInt(str.substring(str.length() - 2));
            if (seconds < 0 || seconds >= SECONDS_IN_MINUTE) {
                throw new RuntimeException("Seconds must be an int between 00 and 59");
            }
        } catch (RuntimeException e) {
            System.err.println("Error occurred while parsing seconds: " + e.getMessage());
            return -1;
        }

        try {
            if (str.charAt(str.length() - 3) != ':') {
                throw new RuntimeException("Given string doesn't match the format for time mm:ss");
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return -1;
        }

        int minutes;
        try {
            minutes = Integer.parseInt(str.substring(0, str.length() - 3));
            if (minutes < 0) {
                throw new RuntimeException("Minutes must be a non-negative int");
            }
        } catch (RuntimeException e) {
            System.err.println("Error occurred while parsing minutes: " + e.getMessage());
            return -1;
        }

        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
