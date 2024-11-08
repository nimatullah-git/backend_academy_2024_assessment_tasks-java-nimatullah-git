package org.example.module4;

import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Pattern;

import static org.example.module4.AbstractFilter.largerThan;
import static org.example.module4.AbstractFilter.regexContains;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter largerThan(long size) {
        return path -> Files.isRegularFile(path) && Files.size(path) > size;
    }

    static AbstractFilter extension(String ext) {
        return path -> {
            String fileName = path.getFileName().toString();
            return fileName.endsWith("." + ext);
        };
    }

    static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).find();
    }

    static AbstractFilter magicNumber(int... bytes) {
        return path -> {
            if (!Files.isRegularFile(path)) return false;
            try (var inputStream = Files.newInputStream(path)) {
                for (int expectedByte : bytes) {
                    if (inputStream.read() != expectedByte) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return matcher::matches;
    }

    @Override
    boolean accept(Path path) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return path -> this.accept(path) && other.accept(path);
    }
}

class AbstractFilterAPI {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("some/directory");

        DirectoryStream.Filter<Path> filter = regularFile
                .and(readable)
                .and(largerThan(100_000))
                .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'))
                .and(AbstractFilter.globMatches("*.png"))
                .and(regexContains("[-]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        }
    }
}
