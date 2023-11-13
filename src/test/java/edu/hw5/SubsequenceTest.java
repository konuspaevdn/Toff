package edu.hw5;

import edu.hw5.Task6.Subsequence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubsequenceTest {
    @Test
    @DisplayName("SubsequenceTest")
    void checkIfSubsequenceTest() {
        String S = "abc";
        String T = "achfdbaabgabcaabg";
        assertThat(Subsequence.checkIfSubsequence(S, T)).isTrue();

        S = "acg";
        T = "abcdefg";
        assertThat(Subsequence.checkIfSubsequence(S, T)).isTrue();

        S = "aabb";
        T = "lalabeach";
        assertThat(Subsequence.checkIfSubsequence(S, T)).isFalse();
    }
}
