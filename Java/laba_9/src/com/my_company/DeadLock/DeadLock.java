package com.my_company.DeadLock;

import com.my_company.A.A;
import com.my_company.B.B;

public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    public DeadLock() {
        Thread myThread = new Thread(this, "MyThread");
        myThread.start();
        a.function1(b);
    }

    public void run() {
        b.function2(a);
    }
}
