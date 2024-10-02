package com.my_company.usual_matrix;

import com.my_company.matrix.Matrix;
import com.my_company.imatrix.IMatrix;

public class UsualMatrix extends Matrix implements IMatrix {
	protected int array[][];

	public UsualMatrix(int row, int column) {
		//super(row,column);
		if (row < 0 || column < 0)
			throw new RuntimeException("Went beyond the matrix");
		array = new int[row][];
		for (int i = 0; i < row; i++) {
			array[i] = new int[column];
		}
		this.row = row;
		this.column = column;

	}
	


	public void setElement(int row, int column, int value) {
		if ((row < 0 || column < 0) && (row > this.row) || (column > this.column))
			throw new RuntimeException("Went beyond the matrix!");
		this.array[row][column] = value;
	}

	public int getElement(int row, int column) {
		if ((row < 0 || column < 0) && (row > this.row) || (column > this.column))
			throw new RuntimeException("Went beyond the matrix!");
		return array[row][column];
	}

	 public IMatrix getInstance(int rows, int columns) {
	        return new UsualMatrix(rows, columns);
	    }

}
