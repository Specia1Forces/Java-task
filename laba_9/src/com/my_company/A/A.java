package com.my_company.A;

import com.my_company.B.B;

public class A {
    int a = 0;

    public synchronized void function1(B b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " function1()");

        try {
            Thread.sleep(500);
        } catch (Exception е) {
            System.out.println(" Клacc А прерван");
        }
        System.out.println(name + " пытается вызвать метод а.add2");
        b.add2();
    }

    public synchronized void add1() {
        System.out.println(" B методе а.add1 ");
    }

}
