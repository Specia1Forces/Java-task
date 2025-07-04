package info.kgeorgiy.ja.stolbov.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;


import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;


public class HelloUDPClient implements HelloClient {

    /*
    private final static int BUFFER_SIZE = 1024; //128 256
    private static final int TIMEOUT = 100;
    ExecutorService executor;





    @Override
    public void run(String host, int port, String prefix, int threads, int requests) {

    }

     */

    public static final int TIMEOUT = 200;

    /**
     * Creates and runs HelloUDPClient.
     *
     * @param args First argument: name or ip-address of the computer on which the server is running.
     *             Second argument: port number to send requests to.
     *             Third argument: request prefix(string).
     *             Fourth argument: the number of parallel request streams.
     *             Fifth argument: the number of requests in each thread.
     */


    public static void main(String[] args) {
        if (args == null || args.length != 5) {
            System.err.println("Incorrect input.");
            return;
        }
        if (checkArguments(args)) {
            System.err.println("Arguments can't be null.");
            return;
        }
        String host = args[0];
        try {
            int port = Integer.parseInt(args[1]);
            String prefix = args[2];
            int threads = Integer.parseInt(args[3]);
            int requests = Integer.parseInt(args[4]);
            HelloClient client = new HelloUDPClient();
            client.run(host, port, prefix, threads, requests);
        } catch (NumberFormatException e) {
            System.err.println("Can't parse number." + e.getMessage());
        }
    }

    private static boolean checkArguments(String[] args) {
        for (String arg : args) {
            if (arg == null) {
                return false;
            }
        }
        return true;
    }




}
