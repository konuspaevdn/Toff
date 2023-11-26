package edu.hw6;

import edu.hw6.Task2.Clone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CloneTest {

    @Test
    @DisplayName("Clone path test")
    void clonePathTest() throws IOException {
        Path path = Paths.get("src/test/resources/Tinkoff secret code.txt");
        File file = new File(path.toString());
        assertThat(file.exists()).isTrue();
        Clone.cloneFile(path);
        File copy1 = new File("src/test/resources/Tinkoff secret code - копия.txt");
        assertThat(copy1.exists()).isTrue();
        InputStream reader = new BufferedInputStream(new FileInputStream(path.toString()));
        byte[] buffer = new byte[8];
        reader.read(buffer);
        String password = new String(buffer);
        assertThat(password).isEqualTo("Maclanka");
        reader.close();

        Clone.cloneFile(path);
        File copy2 = new File("src/test/resources/Tinkoff secret code - копия (2).txt");
        assertThat(copy2.exists()).isTrue();
    }
}
