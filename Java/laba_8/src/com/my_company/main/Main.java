package com.my_company.main;




import com.my_company.i_matrix.IMatrix;
import com.my_company.parallel_matrix_product.ParallelMatrixProduct;
import com.my_company.usual_matrix.UsualMatrix;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main thread started...");
		int size = 1500;
		UsualMatrix m1 = new UsualMatrix(size, size);
		UsualMatrix m2 = new UsualMatrix(size, size);

		m1.randomize(100);
		m2.randomize(100);

		//System.out.println(m1.toString());
		//System.out.println(m2.toString());
		long temp1, temp2;

		temp1 = System.currentTimeMillis();
		ParallelMatrixProduct myThread = new ParallelMatrixProduct(m1, m2, 4);
		temp2 = System.currentTimeMillis();

		System.out.println("Время1 =" + (temp2 - temp1));

		IMatrix m3 = myThread.getResult();
		//System.out.println("Матрица1\n" + m3);

		temp1 = System.currentTimeMillis();
		IMatrix m4 = m1.product(m2);
		temp2 = System.currentTimeMillis();

		//System.out.println("Матрица2\n" + m4);

		System.out.println("Время2 =" + (temp2 - temp1));
		System.out.println("Равны? " + m3.equals(m4));
		System.out.println("Main thread finished...");
	}
}