package info.kgeorgiy.ja.stolbov.bank.rmi;


public class AbstractAccount implements Account {
    protected String id;
    protected int amount;

    public AbstractAccount(String id) {
        this.id = id;
        this.amount = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public synchronized int getAmount() {
        return amount;
    }

    @Override
    public synchronized void setAmount(final int amount) {
        this.amount = amount;
    }
}
