package edu.hw6.Task2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class Clone {
    private Clone() {

    }

    private static final int BUFFER_SIZE = 16;

    public static String cloneFile(Path path) throws IOException {
        String fileName = path.getFileName().toString();
        int dot = fileName.indexOf(".");
        String name = fileName.substring(0, dot);
        String extension = fileName.substring(dot);
        String copy = " - копия";
        String candidate = name + copy + extension;
        Path copyPath = path.resolveSibling(candidate);
        File copyFile = new File(copyPath.toString());
        if (copyFile.isFile()) {
            int copyNum = 2;
            while (true) {
                candidate = name + copy + " (" + copyNum + ")" + extension;
                copyPath = path.resolveSibling(candidate);
                copyFile = new File(copyPath.toString());
                if (!copyFile.isFile()) {
                    break;
                }
                ++copyNum;

            }
        }
        copyFile.createNewFile();
        try (
            InputStream in = new BufferedInputStream(new FileInputStream(path.toString()));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(copyFile))) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int lengthRead;
                while ((lengthRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, lengthRead);
                    out.flush();
                }
        }
        return candidate;
    }
}
