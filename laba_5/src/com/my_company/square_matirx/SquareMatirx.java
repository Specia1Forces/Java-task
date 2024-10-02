package com.my_company.square_matirx;

import com.my_company.imatrix.IMatrix;
import com.my_company.usual_matrix.*;

public class SquareMatirx extends UsualMatrix {


	public SquareMatirx(int size) {
		super(size, size);
		for (int i = 0; i < row; i++) {
			array[i][i] = 1;
		}
		this.row = size;
		this.column = size;
	}
	
	@Override
	public IMatrix getInstance(int rows, int columns) {
		return new SquareMatirx(rows);
	}
	
	/*@Override
	public boolean equals(Object o) {
		if (this.hashCode() != o.hashCode())
			return false;
		if (o instanceof SquareMatirx) {
			SquareMatirx a = (SquareMatirx) o;
			if ((this.row != a.row) || (this.column != a.column)) {
				return false;
			}
			for (int i = 0; i < this.row; i++) {
				for (int j = 0; j < this.column; j++) {
					if (this.getElement(i, j) != a.getElement(i, j)) {
						return false;
					}
				}
			}
			return true;
		} else
			return false;
	}*/

}