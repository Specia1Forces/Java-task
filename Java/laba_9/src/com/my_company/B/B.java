package com.my_company.B;

import com.my_company.A.A;

public class B {
    int b = 0;

    public synchronized void function2(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " function2");
        try {
            Thread.sleep(500);
        } catch (Exception е) {
            System.out.println(" Клacc В прерван");
        }
        System.out.println(name + " пытается вызвать метод a.add1");
        a.add1();
    }

    public synchronized void add2() {
        System.out.println("B методе b.add2");
        System.out.println();
    }

}