package info.kgeorgiy.ja.stolbov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HelloUDPServer implements HelloServer {


    public static final String CLOSE = "CLOSE";
    public static final int TIMEOUT = 50;
    private ExecutorService executors;
    private DatagramSocket socket;
    private static final String HELLO = "Hello, ";

    private void init(int port, int threads) {

    }

    @Override
    public void start(int port, int threads) {

    }

    @Override
    public void close() {

    }






}
