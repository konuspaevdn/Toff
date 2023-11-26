package edu.hw3;

import edu.hw3.Task1.Atbash;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtbashTest {
    @Test
    @DisplayName("Strings from only letters")
    void AtbashCipherLetters() {
        String str1 = "Helloworld";
        String str2 = "SuperMarioBrosWonder";
        String str3 = "abcdefghijklmnopqrstuvwxyz";
        String str4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        assertThat(Atbash.cipher(str1)).isEqualTo("Svooldliow");
        assertThat(Atbash.cipher(str2)).isEqualTo("HfkviNzirlYilhDlmwvi");
        assertThat(Atbash.cipher(str3)).isEqualTo("zyxwvutsrqponmlkjihgfedcba");
        assertThat(Atbash.cipher(str4)).isEqualTo("ZYXWVUTSRQPONMLKJIHGFEDCBA");
    }

    @Test
    @DisplayName("Basic strings and characters")
    void AtbashCipherAnything() {
        String str1 = "0123456789!@#$%^&*()-=_+ /.,<>?~'|\"";
        String str2 = "Any fool can write code that a computer can understand. " +
                      "Good programmers write code that humans can understand. ― Martin Fowler";

        assertThat(Atbash.cipher(str1)).isEqualTo(str1);
        assertThat(Atbash.cipher(str2)).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

    @Test
    @DisplayName("Nilpotency")
    void AtbashCipherEvenNilpotency() {
        String str = "Hello!";
        assertThat(Atbash.cipher(Atbash.cipher(str))).isEqualTo(str);
    }
}
