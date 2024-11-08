package org.example.module4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractFilterTest {
    private static final Path TEST_DIR = Paths.get("testDir");
    private static final Path READABLE_FILE = TEST_DIR.resolve("readableFile.txt");
    private static final Path LARGE_FILE = TEST_DIR.resolve("largeFile.txt");

    @BeforeEach
    void setUp() throws Exception {
        Files.createDirectories(TEST_DIR);
        Files.createFile(READABLE_FILE);
        Files.writeString(READABLE_FILE, "Test content");
        Files.createFile(LARGE_FILE);
        Files.writeString(LARGE_FILE, "Large file content repeated".repeat(5000));
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.walk(TEST_DIR)
                .map(Path::toFile)
                .forEach(file -> {
                    if (!file.delete()) file.deleteOnExit();
                });
    }

    @Test
    void shouldFilterReadableFiles() throws Exception {
        AbstractFilter readableFilter = AbstractFilter.readable();

        assertThat(readableFilter.accept(READABLE_FILE)).isTrue();
    }

    @Test
    void shouldFilterLargerFiles() throws Exception {
        AbstractFilter largeFilter = AbstractFilter.largerThan(100_000);

        assertThat(largeFilter.accept(LARGE_FILE)).isTrue();
        assertThat(largeFilter.accept(READABLE_FILE)).isFalse();
    }

    @Test
    void shouldFilterByExtension() throws Exception {
        AbstractFilter txtFilter = AbstractFilter.extension("txt");

        assertThat(txtFilter.accept(READABLE_FILE)).isTrue();
        assertThat(txtFilter.accept(TEST_DIR)).isFalse();
    }

    @Test
    void shouldFilterByMagicNumber() throws Exception {
        Files.write(LARGE_FILE, new byte[]{(byte) 0x89, 'P', 'N', 'G'});
        AbstractFilter pngFilter = AbstractFilter.magicNumber(0x89, 'P', 'N', 'G');

        assertThat(pngFilter.accept(LARGE_FILE)).isTrue();
    }
}
