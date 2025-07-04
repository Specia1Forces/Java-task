package info.kgeorgiy.ja.stolbov.bank.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {

    Account createAccount(String passportId, String subId) throws RemoteException;

    Account getAccount(String passportId, String subId) throws RemoteException;

    Person getPersonByTypePerson(String passportId, String chooseType) throws RemoteException;

    Person getRemotePerson(String passportId) throws RemoteException;

    Person getLocalPerson(String passportId) throws RemoteException;

    Person createPerson(String passportId, String firstName, String secondName) throws RemoteException;
}

