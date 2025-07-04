package info.kgeorgiy.ja.stolbov.bank.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RemoteBank implements Bank {
    private final int port;
    //private final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Person> persons = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, ConcurrentMap<String, Account>> accounts = new ConcurrentHashMap<>();

    public RemoteBank(final int port) {
        this.port = port;
    }

    @Override
    public Account createAccount(String passportId, String subId) throws RemoteException {
        String accountId = passportId + ":" + subId;
        final Account account = new RemoteAccount(accountId);

        if (accounts.containsKey(passportId)) {
            ConcurrentMap<String, Account> allAccounts = accounts.get(passportId);
            if (allAccounts.putIfAbsent(accountId, account) == null) {

                UnicastRemoteObject.exportObject(account, port);
                accounts.put(passportId, allAccounts);

                return account;
            } else {
                return getAccount(passportId, subId);
            }
        } else {
            ConcurrentMap<String, Account> newAccounts = new ConcurrentHashMap<>();
            newAccounts.put(accountId, account);

            UnicastRemoteObject.exportObject(account, port);

            accounts.put(passportId, newAccounts);
            return account;
        }

    }

    @Override
    public Account getAccount(String passportId, String subId) {
        String accountId = passportId + ":" + subId;
        if (accounts.containsKey(passportId)) {
            ConcurrentMap<String, Account> allAccounts = accounts.get(passportId);
            if (allAccounts.containsKey(accountId)) {
                return allAccounts.get(accountId);
            }
        }
        return null;
    }

    @Override
    public Person getRemotePerson(String passportId) throws RemoteException {
        return persons.get(passportId);
    }

    @Override
    public Person getLocalPerson(String passportId) throws RemoteException {
        Person remotePerson = persons.get(passportId);
        if (persons.get(passportId) == null) {
            return null;
        }
        return new LocalPerson(remotePerson.getPassportId(), remotePerson.getFirstName(), remotePerson.getSecondName(), cloneAccount(getAccountsForLocalPersons(passportId)));
    }


    @Override
    public Person createPerson(String passportId, String firstName, String secondName) throws RemoteException {


        final Person person = new RemotePerson(passportId, firstName, secondName);
        if (persons.putIfAbsent(passportId, person) == null) {
            UnicastRemoteObject.exportObject(person, port);
            return person;
        } else {
            return getRemotePerson(passportId);
        }
    }

    @Override
    public Person getPersonByTypePerson(String passportId, String chooseType) throws RemoteException {
        if (Objects.equals(chooseType, "localPerson")) {
            return getLocalPerson(passportId);
        }
        return getRemotePerson(passportId);
    }

    private ConcurrentMap<String, Account> cloneAccount(ConcurrentMap<String, Account> accounts) {
        ConcurrentMap<String, Account> localAccounts = new ConcurrentHashMap<>();
        accounts.forEach((id, account) -> {
            try {
                localAccounts.put(id, new LocalAccount(account.getId(), account.getAmount()));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        return localAccounts;
    }

    private ConcurrentMap<String, Account> getAccountsForLocalPersons(String passportId) {
        if (accounts.containsKey(passportId)) {
            return accounts.get(passportId);
        }
        return null;
    }
}
