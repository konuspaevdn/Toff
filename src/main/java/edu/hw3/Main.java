package edu.hw3;

import edu.hw3.Task2.BracketSequence;
import edu.hw3.Task2.UnbalancedBSException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) throws UnbalancedBSException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        LOGGER.info("Hello and welcome!");
        for (String str : BracketSequence.clusterize("((()))(())()()(()())")) {
            LOGGER.info(str);
        }
    }
}
