package info.kgeorgiy.ja.stolbov.bank.rmi;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractPerson implements Person {
    protected String passportId;
    protected String firstName;
    protected String secondName;

    public AbstractPerson(String passportId, String firstName, String secondName) {
        this.passportId = passportId;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String getPassportId() {
        return passportId;
    }

    @Override
    public synchronized String getFirstName() {
        return firstName;
    }

    @Override
    public synchronized String getSecondName() {
        return secondName;
    }

}
