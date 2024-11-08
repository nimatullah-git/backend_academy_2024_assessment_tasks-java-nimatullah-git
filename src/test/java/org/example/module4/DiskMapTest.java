package org.example.module4;

import org.junit.jupiter.api.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiskMapTest {
    private static final String TEST_FILE = "testDiskMap.txt";
    private DiskMap diskMap;

    @BeforeEach
    void setUp() throws Exception {
        diskMap = new DiskMap(TEST_FILE);
        diskMap.clear(); // Очистка файла перед каждым тестом
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    @DisplayName("Should add and retrieve a key-value pair")
    void shouldAddAndRetrieveKeyValuePair() {
        String key = "key1";
        String value = "value1";

        diskMap.put(key, value);

        assertThat(diskMap.get(key)).isEqualTo(value);
    }

    @Test
    @DisplayName("Should remove a key-value pair")
    void shouldRemoveKeyValuePair() {
        String key = "key2";
        String value = "value2";
        diskMap.put(key, value);

        String removedValue = diskMap.remove(key);

        assertThat(removedValue).isEqualTo(value);
        assertThat(diskMap.get(key)).isNull();
    }

    @Test
    @DisplayName("Should correctly report size and empty state")
    void shouldReportSizeAndEmptyState() {
        boolean isInitiallyEmpty = diskMap.isEmpty();
        diskMap.put("key3", "value3");

        assertThat(isInitiallyEmpty).isTrue();
        assertThat(diskMap.size()).isEqualTo(1);
        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Should persist data to disk")
    void shouldPersistDataToDisk() throws Exception {
        String key = "key4";
        String value = "value4";
        diskMap.put(key, value);

        DiskMap reloadedMap = new DiskMap(TEST_FILE);

        assertThat(reloadedMap.get(key)).isEqualTo(value);
    }
}
