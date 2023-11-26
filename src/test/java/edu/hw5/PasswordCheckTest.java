package edu.hw5;

import edu.hw5.Task4.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class PasswordCheckTest {
    @Test
    @DisplayName("Check the password checker")
    void checkPasswordTest() {
        assertThat(Password.check("12345")).isFalse();
        assertThat(Password.check("~12345")).isTrue();
        assertThat(Password.check("1!2345")).isTrue();
        assertThat(Password.check("12@345")).isTrue();
        assertThat(Password.check("123#45")).isTrue();
        assertThat(Password.check("1234$5")).isTrue();
        assertThat(Password.check("12345%")).isTrue();
        assertThat(Password.check("qwerty")).isFalse();
        assertThat(Password.check("^qwerty")).isTrue();
        assertThat(Password.check("qwer&ty")).isTrue();
        assertThat(Password.check("qwerty*")).isTrue();
        assertThat(Password.check("qwe|rty")).isTrue();
        assertThat(Password.check("qwerty.")).isFalse();
    }
}
