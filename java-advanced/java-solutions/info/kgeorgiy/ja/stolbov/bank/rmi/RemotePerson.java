package info.kgeorgiy.ja.stolbov.bank.rmi;

import java.rmi.RemoteException;

public class RemotePerson extends AbstractPerson {
    public RemotePerson(String passportId, String firstName, String secondName) {
        super(passportId, firstName, secondName);
    }

}
