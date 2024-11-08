package org.example.module4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class FileCloner {

    public static Path cloneFile(Path path) throws IOException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Файл не существует или является недопустимым типом: " + path);
        }

        Path directory = path.getParent();
        String originalFileName = path.getFileName().toString();
        String baseName = extractBaseName(originalFileName);
        String extension = extractExtension(originalFileName);

        Path newFilePath = generateUniqueFilePath(directory, baseName, extension);
        Files.copy(path, newFilePath);
        return newFilePath;
    }

    private static String extractBaseName(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? fileName : fileName.substring(0, lastDotIndex);
    }

    private static String extractExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex);
    }

    private static Path generateUniqueFilePath(Path directory, String baseName, String extension) {
        Path newFilePath = directory.resolve(baseName + " — копия" + extension);
        if (!Files.exists(newFilePath)) {
            return newFilePath;
        }

        int copyIndex = 2;
        Pattern pattern = Pattern.compile(Pattern.quote(baseName) + " — копия(?: \\((\\d+)\\))?" + Pattern.quote(extension));

        try {
            while (true) {
                String newFileName = String.format("%s — копия (%d)%s", baseName, copyIndex, extension);
                newFilePath = directory.resolve(newFileName);
                if (!Files.exists(newFilePath)) {
                    break;
                }
                copyIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFilePath;
    }
}
