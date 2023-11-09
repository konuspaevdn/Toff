package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.Trace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Helper {

    Exception causeException() {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            return e;
        }
    }
}

public class TraceTest {

    @Test
    @DisplayName("Trace testing")
    void testCallingInfo() {
        CallingInfo ci1 = new CallingInfo("edu.hw2.TraceTest", "testCallingInfo");
        assertThat(Trace.trace(new Throwable())).isEqualTo(ci1);

        CallingInfo ci2 = new CallingInfo("edu.hw2.Helper", "causeException");
        assertThat(Trace.trace(new Helper().causeException())).isEqualTo(ci2);
    }

}
