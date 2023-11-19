package edu.hw6.Task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final Map<String, Integer> indices = new HashMap<>();

    private final Map<String, Integer> length = new HashMap<>();

    private final Path pathToFile;

    private final String delimiter = ":";

    private final RandomAccessFile file;

    public DiskMap(String name) throws IOException {
        String p = "src/test/resources/";
        Path path = Paths.get(p);
        Files.createDirectories(path);
        pathToFile = path.resolve(name);
        file = new RandomAccessFile(pathToFile.toString(), "rw");
        file.setLength(0);
    }

    @Override
    public int size() {
        return indices.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return indices.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (var key : indices.keySet()) {
            if (get(key).equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String get(Object key) {
        String response = null;
        if (indices.containsKey(key.toString())) {
            try {
                file.seek(indices.get(key) + key.toString().getBytes().length + delimiter.getBytes().length);
                int responseLength = length.get(key);
                byte[] b = new byte[responseLength];
                file.read(b, 0, responseLength);
                response = new String(b);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String response = get(key);
        Path newPath = pathToFile.resolveSibling(key);
        if (response == null) {
            try {
                Files.createFile(newPath);
                BufferedWriter writer = new BufferedWriter(new FileWriter(newPath.toString()));
                writer.write(value);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                new PrintWriter(newPath.toString()).close();
                BufferedWriter writer = new BufferedWriter(new FileWriter(newPath.toString()));
                writer.write(value);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            indices.put(key, (int) file.length());
            length.put(key, value.getBytes().length);
            file.seek(file.length());
            file.write(key.getBytes());
            file.write(delimiter.getBytes());
            file.write(value.getBytes());
            file.write("\n".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public String remove(Object key) {
        String response = get(key);
        if (response != null) {
            Path path = pathToFile.resolveSibling(key.toString());
            File keyFile = new File(path.toString());
            keyFile.delete();
        }
        indices.remove(key);
        length.remove(key);
        return response;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var l : m.entrySet()) {
            put(l.getKey(), l.getValue());
        }
    }

    @Override
    public void clear() {
        for (var key : keySet()) {
            Path path = pathToFile.resolveSibling(key);
            File keyFile = new File(path.toString());
            keyFile.delete();
        }
        indices.clear();
        length.clear();
        try {
            file.setLength(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return indices.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        List<String> values = new ArrayList<>(indices.size());
        for (var key : indices.keySet()) {
            values.add(get(key));
        }
        return values;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> entrySet = new HashSet<>(indices.size());
        for (var key : indices.keySet()) {
            Entry<String, String> entry = new AbstractMap.SimpleEntry<>(key, get(key));
            entrySet.add(entry);
        }
        return entrySet;
    }

    public void close() throws IOException {
        file.close();
    }


}
