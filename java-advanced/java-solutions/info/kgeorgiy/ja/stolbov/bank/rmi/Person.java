package info.kgeorgiy.ja.stolbov.bank.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentMap;

public interface Person extends Remote {
    public String getPassportId() throws RemoteException;

    public String getFirstName() throws RemoteException;

    public String getSecondName() throws RemoteException;

}
