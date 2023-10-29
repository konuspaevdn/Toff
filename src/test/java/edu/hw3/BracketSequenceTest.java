package edu.hw3;

import edu.hw3.Task2.BracketSequence;
import edu.hw3.Task2.UnbalancedBSException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BracketSequenceTest {
    @Test
    @DisplayName("Clusterizing correct bs")
    void clusterizeCorrectBS() throws UnbalancedBSException {
        String seq1 = "()()()";
        ArrayList<String> cl1 = new ArrayList<>(Arrays.asList(new String[]{"()", "()", "()"}));
        assertThat(BracketSequence.clusterize(seq1)).isEqualTo(cl1);

        String seq2 = "((()))";
        ArrayList<String> cl2 = new ArrayList<>(Arrays.asList(new String[]{"((()))"}));
        assertThat(BracketSequence.clusterize(seq2)).isEqualTo(cl2);

        String seq3 = "((()))(())()()(()())";
        ArrayList<String> cl3 = new ArrayList<>(Arrays.asList(new String[]{"((()))", "(())", "()", "()", "(()())"}));
        assertThat(BracketSequence.clusterize(seq3)).isEqualTo(cl3);
    }

    @Test
    @DisplayName("Check incorrect bs")
    void checkIncorrectBS() throws UnbalancedBSException {
        String seq1 = "())()";

    }
}
