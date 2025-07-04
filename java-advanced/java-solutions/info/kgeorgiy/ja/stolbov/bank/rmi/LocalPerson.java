package info.kgeorgiy.ja.stolbov.bank.rmi;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocalPerson extends AbstractPerson implements Serializable {
    private ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();

    public LocalPerson(String passportId, String firstName, String secondName, ConcurrentMap<String, Account> accounts) {
        super(passportId, firstName, secondName);
        accounts.forEach((id, account) -> this.accounts = accounts);
    }

    public synchronized ConcurrentMap<String, Account> getAccounts() {
        return accounts;
    }

    public synchronized Account findAccountSubId(String subId) {
        String accountId = passportId + ":" + subId;
        if (accounts.containsKey(accountId)) {
            return accounts.get(accountId);
        }
        return null;
    }


}
