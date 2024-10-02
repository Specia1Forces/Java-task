package com.my_company.sparse_matrix;

import com.my_company.matrix.*;

import java.util.*;
import java.util.LinkedList;

import com.my_company.imatrix.IMatrix;;

public class SparseMatrix extends Matrix implements IMatrix {
	LinkedList<Element> sparseArray;

	private class Element {
		int x;
		int y;
		int value;

		Element(int row, int col, int value) {
			this.x = row;
			this.y = col;
			this.value = value;
		}
	}

	public SparseMatrix(int row, int column) {

		if (row < 0 || column < 0)
			throw new RuntimeException("Went beyond the matrix");
		sparseArray = new LinkedList<Element>();
		this.row = row;
		this.column = column;

	}

	public void setElement(int row, int column, int value) {
		if ((row < this.row || column < this.column) && (row > this.row) || (column > this.column))
			throw new RuntimeException("Went beyond the matrix!");

		ListIterator<Element> i = sparseArray.listIterator();
		while (i.hasNext()) {
			Element element = (Element) i.next();
			if (element.x == row && element.y == column) {
				element.value = value;
				i.set(element);
				;
				return;
			}
		}
		Element a = new Element(row, column, value);
		i.add(a);

	}

	public int getElement(int row, int column) {
		if ((row < 0 || column < 0) && (row > this.row) || (column > this.column))
			throw new RuntimeException("Went beyond the matrix!");
		for (Iterator<Element> i = sparseArray.iterator(); i.hasNext();) {
			Element element = (Element) i.next();
			if (element.x == row && element.y == column) {
				return element.value;
			}
		}
		return 0;
	}



	@Override
	public IMatrix getInstance(int row, int column) {
		return new SparseMatrix(row, column);
	}
}