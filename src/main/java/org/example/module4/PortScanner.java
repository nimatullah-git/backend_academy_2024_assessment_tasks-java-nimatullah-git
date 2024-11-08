package org.example.module4;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

public class PortScanner {
    // Список известных портов и сервисов
    public static final Map<Integer, String> knownTcpPorts = Map.of(
            80, "HTTP",
            443, "HTTPS",
            135, "EPMAP",
            139, "NetBIOS Session Service",
            445, "Microsoft-DS Active Directory",
            843, "Adobe Flash",
            17500, "Dropbox",
            27017, "MongoDB"
    );
    public static final Map<Integer, String> knownUdpPorts = Map.of(
            53, "DNS",
            137, "NetBIOS Name Service",
            138, "NetBIOS Datagram Service",
            1900, "SSDP",
            3702, "Web Service Discovery",
            5353, "Multicast DNS",
            5355, "LLMNR",
            17500, "Dropbox"
    );
    private static final int MAX_PORT = 49151;

    public static void main(String[] args) {
        System.out.println("Протокол\tПорт\tСервис");

        for (int port = 0; port <= MAX_PORT; port++) {
            // Проверка TCP-портов
            if (isTcpPortAvailable(port)) {
                System.out.printf("TCP\t\t%d\t%s\n", port, knownTcpPorts.getOrDefault(port, ""));
            }

            // Проверка UDP-портов
            if (isUdpPortAvailable(port)) {
                System.out.printf("UDP\t\t%d\t%s\n", port, knownUdpPorts.getOrDefault(port, ""));
            }
        }
    }

    public static boolean isTcpPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            return true; // Порт доступен
        } catch (IOException e) {
            return false; // Порт занят
        }
    }

    public static boolean isUdpPortAvailable(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            datagramSocket.setReuseAddress(true);
            return true; // Порт доступен
        } catch (IOException e) {
            return false; // Порт занят
        }
    }
}
