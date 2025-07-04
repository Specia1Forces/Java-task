package info.kgeorgiy.ja.stolbov.bank;

import info.kgeorgiy.ja.stolbov.bank.rmi.Account;
import info.kgeorgiy.ja.stolbov.bank.rmi.Bank;
import info.kgeorgiy.ja.stolbov.bank.rmi.Person;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public final class Client {
    /**
     * Utility class.
     */
    private Client() {
    }

    public static void main(final String... args) throws RemoteException, NotBoundException {
        if (args.length < 5) {
            System.out.println("Enter parameters:  <firstName> <secondName> <passport> <subId> <delta>");
            return;
        }
        String firstName = args[0];
        String secondName = args[1];
        String passportId = args[2];
        String subId = args[3];
        int delta = Integer.parseInt(args[4]);

        Registry registry = LocateRegistry.getRegistry(2732);
        Bank bank = (Bank) registry.lookup("//localhost/bank");

        Person person = bank.getRemotePerson(passportId);

        if (person == null) {
            bank.createPerson(passportId, firstName, secondName);
            person = bank.getRemotePerson(passportId);
            if (person == null) {
                System.out.println("Failed to create a human!");
                return;
            }
        }


        Account account = bank.getAccount(passportId, subId);
        if (account == null) {
            bank.createAccount(passportId, subId);
            account = bank.getAccount(passportId, subId);
        }

        if (account == null) {
            System.out.println("Error creating or receiving an invoice.");
            return;
        }

        int oldAmount = account.getAmount();
        System.out.println("Old balance: " + account.getAmount());

        int newAmount = oldAmount + delta;

        account.setAmount(newAmount);

        System.out.println("New balance: " + account.getAmount());

    }
}
