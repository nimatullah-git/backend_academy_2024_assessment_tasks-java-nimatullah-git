package org.example.module4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap implements Map<String, String> {
    private final Map<String, String> map = new HashMap<>();
    private final Path filePath;

    public DiskMap(String fileName) throws IOException {
        this.filePath = Paths.get(fileName);
        if (Files.exists(filePath)) {
            loadFromFile();
        }
    }

    private void loadFromFile() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }
        }
    }

    private void saveToFile() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }
    }

    @Override
    public String put(String key, String value) {
        String previousValue = map.put(key, value);
        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return previousValue;
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String remove(Object key) {
        String removedValue = map.remove(key);
        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removedValue;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        map.clear();
        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
