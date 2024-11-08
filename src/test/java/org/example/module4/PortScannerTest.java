package org.example.module4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PortScannerTest {
    private static final int TEST_PORT = 54321; // Убедитесь, что этот порт свободен на тестовой машине

    @Test
    void shouldDetectTcpPortIsAvailable() {
        boolean isAvailable = PortScanner.isTcpPortAvailable(TEST_PORT);

        assertThat(isAvailable).isTrue();
    }

    @Test
    void shouldDetectTcpPortIsOccupied() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(TEST_PORT)) {
            serverSocket.setReuseAddress(true);

            boolean isAvailable = PortScanner.isTcpPortAvailable(TEST_PORT);

            assertThat(isAvailable).isFalse();
        }
    }

    @Test
    void shouldDetectUdpPortIsAvailable() {
        boolean isAvailable = PortScanner.isUdpPortAvailable(TEST_PORT);

        assertThat(isAvailable).isTrue();
    }

    @Test
    void shouldDetectUdpPortIsOccupied() throws IOException {
        try (DatagramSocket datagramSocket = new DatagramSocket(TEST_PORT)) {
            datagramSocket.setReuseAddress(true);

            boolean isAvailable = PortScanner.isUdpPortAvailable(TEST_PORT);

            assertThat(isAvailable).isFalse();
        }
    }

    @Test
    void shouldDisplayCorrectServiceNamesForKnownPorts() {
        Map<Integer, String> expectedTcpPorts = Map.of(
                80, "HTTP",
                443, "HTTPS",
                135, "EPMAP"
        );
        Map<Integer, String> expectedUdpPorts = Map.of(
                53, "DNS",
                137, "NetBIOS Name Service",
                138, "NetBIOS Datagram Service"
        );

        expectedTcpPorts.forEach((port, serviceName) -> {
            assertThat(PortScanner.knownTcpPorts.get(port)).isEqualTo(serviceName);
        });

        expectedUdpPorts.forEach((port, serviceName) -> {
            assertThat(PortScanner.knownUdpPorts.get(port)).isEqualTo(serviceName);
        });
    }
}
