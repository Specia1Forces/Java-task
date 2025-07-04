package info.kgeorgiy.ja.stolbov.bank.rmi;

import java.io.Serializable;

public class RemoteAccount extends AbstractAccount implements Serializable {

    public RemoteAccount(final String id) {
        super(id);
    }

}
