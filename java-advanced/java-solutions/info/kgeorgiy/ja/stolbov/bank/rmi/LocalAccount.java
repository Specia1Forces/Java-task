package info.kgeorgiy.ja.stolbov.bank.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

public class LocalAccount extends AbstractAccount implements Serializable {

    public LocalAccount(String id, int amount) {
        super(id);
        this.amount = amount;
    }


}
