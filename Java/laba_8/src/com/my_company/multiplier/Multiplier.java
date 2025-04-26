package com.my_company.multiplier;

import com.my_company.i_matrix.IMatrix;

public class Multiplier extends Thread {

    int begin = 0;
    int end = 0;

    IMatrix a;
    IMatrix b;
    IMatrix result ;

    public Multiplier(IMatrix a, IMatrix b, IMatrix result, int begin, int end) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        System.out.printf(" started... \n");
        if (a.getCol() != b.getRow()) {
            throw new RuntimeException("Multiplication is impossible!");
        }
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try {
            if (a.getCol() != b.getRow()) {
                throw new RuntimeException("Multiplication is impossible!");
            }
            for (int i = begin; i < end; i++) {

                for (int j = 0; j < b.getCol(); j++) {

                    for (int k = 0; k < a.getCol(); k++) {
                        result.setElement(i, j, result.getElement(i, j) + a.getElement(i, k) * b.getElement(k, j));
                    }

                }
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
