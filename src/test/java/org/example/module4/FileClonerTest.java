package org.example.module4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class FileClonerTest {
    private static final Path TEST_DIR = Paths.get("testDir");
    private static final Path ORIGINAL_FILE = TEST_DIR.resolve("Tinkoff Bank Biggest Secret.txt");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(TEST_DIR);
        Files.createFile(ORIGINAL_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(TEST_DIR)
                .map(Path::toFile)
                .forEach(file -> {
                    if (!file.delete()) {
                        file.deleteOnExit();
                    }
                });
    }

    @Test
    @DisplayName("Should create first copy with 'копия' suffix")
    void shouldCreateFirstCopyWithCopySuffix() throws IOException {
        Path copiedFile = FileCloner.cloneFile(ORIGINAL_FILE);

        assertThat(copiedFile.getFileName().toString())
                .isEqualTo("Tinkoff Bank Biggest Secret — копия.txt");
        assertThat(Files.exists(copiedFile)).isTrue();
    }

    @Test
    @DisplayName("Should create second copy with '(2)' suffix")
    void shouldCreateSecondCopyWithNumberedSuffix() throws IOException {
        FileCloner.cloneFile(ORIGINAL_FILE);

        Path secondCopy = FileCloner.cloneFile(ORIGINAL_FILE);

        assertThat(secondCopy.getFileName().toString())
                .isEqualTo("Tinkoff Bank Biggest Secret — копия (2).txt");
        assertThat(Files.exists(secondCopy)).isTrue();
    }

    @Test
    @DisplayName("Should create third copy with '(3)' suffix after two existing copies")
    void shouldCreateThirdCopyWithNumberedSuffix() throws IOException {
        FileCloner.cloneFile(ORIGINAL_FILE);
        FileCloner.cloneFile(ORIGINAL_FILE);

        Path thirdCopy = FileCloner.cloneFile(ORIGINAL_FILE);

        assertThat(thirdCopy.getFileName().toString())
                .isEqualTo("Tinkoff Bank Biggest Secret — копия (3).txt");
        assertThat(Files.exists(thirdCopy)).isTrue();
    }
}
