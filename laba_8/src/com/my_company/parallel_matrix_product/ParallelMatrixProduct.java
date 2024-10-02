package com.my_company.parallel_matrix_product;

import com.my_company.i_matrix.IMatrix;
import com.my_company.multiplier.Multiplier;
import com.my_company.usual_matrix.UsualMatrix;

public class ParallelMatrixProduct extends Thread {

    int begin = 0;
    int end = 0;

    IMatrix a;
    IMatrix b;
    UsualMatrix result;

    public ParallelMatrixProduct(IMatrix a, IMatrix b, int n) throws InterruptedException {
        Thread[] myThread = new Thread[n];
        this.a = a;
        this.b = b;
        result = new UsualMatrix(a.getRow(), b.getCol());
        int sizeRow = a.getRow() / n;
        int sizeRowMod = a.getRow() % n;
        int begin = 0;
        int end = sizeRow;
        for (int i = 0; i < n - 1; i++) {
            myThread[i] = new Thread(new Multiplier(a, b, result, begin, end));
            myThread[i].start();
            begin = end;
            end += sizeRow;
        }

        end = a.getRow();

        myThread[n - 1] = new Thread(new Multiplier(a, b, result, begin, end));
        myThread[n - 1].start();

        for (int i = 0; i < n; i++) {
            myThread[i].join();
        }
    }

    public UsualMatrix getResult() {
        return result;
    }
}
