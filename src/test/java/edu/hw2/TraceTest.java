package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.Trace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TraceTest {
    @Test
    @DisplayName("Trace testing")
    void testCallingInfo() {
        CallingInfo ci = new CallingInfo("TraceTest", "testCallingInfo");
        assertThat(Trace.trace()).isEqualTo(ci);
    }

}
