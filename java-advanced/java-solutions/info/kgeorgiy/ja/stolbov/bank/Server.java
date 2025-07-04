package info.kgeorgiy.ja.stolbov.bank;

import info.kgeorgiy.ja.stolbov.bank.rmi.Bank;
import info.kgeorgiy.ja.stolbov.bank.rmi.RemoteBank;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public final class Server {
    private final static int DEFAULT_PORT = 8888;

    public static void main(final String... args) {
        final int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        Registry registry;
        final Bank bank = new RemoteBank(port);
        try {
            registry = LocateRegistry.createRegistry(2732);
            UnicastRemoteObject.exportObject(bank, port);
            registry.rebind("//localhost/bank", bank);
            System.out.println("Server started");
        } catch (final RemoteException e) {
            System.out.println("Cannot export object: " + e.getMessage());
            System.exit(1);
        }
    }
}
