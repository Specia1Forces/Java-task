package com.my_company.main;

import com.my_company.sparse_matrix.*;
import com.my_company.square_matirx.SquareMatirx;
import com.my_company.usual_matrix.*;

public class Main {
	public static void main(String[] args) {
		SparseMatrix mat_1 = new SparseMatrix(2, 2);
		SquareMatirx mat_2 = new SquareMatirx(2);
		UsualMatrix mat_3 = new UsualMatrix(2, 2);

		mat_1.setElement(0, 0, 234);
		mat_1.setElement(0, 1, 342);
		mat_1.setElement(1, 1, -4354);
		mat_1.setElement(1, 0, 53243);
		System.out.println(mat_1.toString());
		mat_2.setElement(0, 0, 5);
		mat_2.setElement(0, 1, 12);
		mat_2.setElement(0, 1, -234);
		mat_2.setElement(1, 0, 5413);
		System.out.println(mat_2.toString());
		mat_3.setElement(0, 0, 1);
		mat_3.setElement(0, 1, 0);
		
		System.out.println(mat_3.toString());
		System.out.println("------------");
		// mat_1.product(mat_1);
		System.out.println(mat_1.product(mat_1).toString());
		// mat_2.product(mat_2);
		System.out.println(mat_2.product(mat_2).toString());
		System.out.println("------------");
		// mat_1.product(mat_1);
		System.out.println("Cумма");
		System.out.println(mat_1.sum(mat_1).toString());
		// mat_2.product(mat_2);
		System.out.println(mat_2.sum(mat_2).toString());

	}
}