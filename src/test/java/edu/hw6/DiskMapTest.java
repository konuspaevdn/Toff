package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiskMapTest {
    @Test
    @DisplayName("Testing DiskMap functionality")
    void Test() throws IOException {
        DiskMap dm = new DiskMap("foo.txt");
        assertThat(dm.size()).isEqualTo(0);
        dm.put("abra.txt", "cadabra");
        assertThat(dm.get("abra.txt")).isEqualTo("cadabra");
        File abra = new File("src/test/resources/abra.txt");
        assertThat(abra.exists()).isTrue();
        dm.put("abra.txt", "sim sala bim");
        assertThat(dm.size()).isEqualTo(1);
        assertThat(dm.get("abra.txt")).isEqualTo("sim sala bim");
        BufferedReader reader = new BufferedReader(new FileReader(abra));
        assertThat(reader.readLine()).isEqualTo("sim sala bim");

        dm.put("akalai.txt", "makalai");
        assertThat(dm.size()).isEqualTo(2);
        dm.put("sesame.txt", "open");
        assertThat(dm.size()).isEqualTo(3);
        assertThat(dm.containsKey("akalai.txt")).isTrue();
        assertThat(dm.containsValue("open")).isTrue();

        dm.remove("sesame.txt");
        assertThat(dm.containsKey("sesame.txt")).isFalse();
        File sesame = new File("src/test/resources/sesame.txt");
        assertThat(sesame.exists()).isFalse();

        dm.clear();
        assertThat(dm.size()).isEqualTo(0);
        assertThat(abra.exists()).isFalse();

        dm.close();
    }
}
