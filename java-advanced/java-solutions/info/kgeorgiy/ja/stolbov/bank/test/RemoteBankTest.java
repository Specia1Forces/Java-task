package info.kgeorgiy.ja.stolbov.bank.test;


import info.kgeorgiy.ja.stolbov.bank.rmi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;

public class RemoteBankTest {
    private RemoteBank bank;

    @BeforeEach
    public void setUp() {
        bank = new RemoteBank(5050);
    }


}


