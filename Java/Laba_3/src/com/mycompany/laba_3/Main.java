package com.mycompany.laba_3;


import com.mycompany.square_matrix.SquareMatrix;
import com.mycompany.usual_matrix.UsualMatrix;

public class Main {
    public static void f1(){
        System.out.println("f1:");

        UsualMatrix um1 = new UsualMatrix(3,3);
        UsualMatrix um2 = new UsualMatrix(3,4);
        SquareMatrix sq1 = new SquareMatrix(3);
        SquareMatrix sq2 = new SquareMatrix(4);

        System.out.println("um1:" + "\n" + um1);
        System.out.println("um2:" + "\n" + um2);
        System.out.println("sq1:" + "\n" + sq1);
        System.out.println("sq2:" + "\n" + sq2);

        um1.setElement(1,2, 4);
        um1.setElement(2,2, 16);
        um1.setElement(0,2, 7);
        um1.setElement(0,0, 104);

        um2.setElement(1,2, 7);
        um2.setElement(1,0, 16);
        um2.setElement(0,3, 70);
        um2.setElement(2,3, 1000);

        sq1.setElement(1,2, 6);
        sq1.setElement(2,1, 8);
        sq1.setElement(0,1, 55);
        sq1.setElement(0,0, 1);

        sq2.setElement(0,0, 2);
        sq2.setElement(2,3, 6);
        sq2.setElement(0,3, 54);
        sq2.setElement(0,2, 12);
        sq2.setElement(1,2, 10);

        System.out.println("um1:" + "\n" + um1);
        System.out.println("um2:" + "\n" + um2);
        System.out.println("sq1:" + "\n" + sq1);
        System.out.println("sq2:" + "\n" + sq2);

        System.out.println("um1 + sq1:" + "\n" + um1.sum(sq1));
        System.out.println("um1 * um2:" + "\n" + um1.product(um2));
        System.out.println("um1 * sq1:" + "\n" + um1.product(sq1));
        System.out.println("um2 * sq2:" + "\n" + um2.product(sq2));
    }

    public static void main(String []args){
        f1();
    }
}
