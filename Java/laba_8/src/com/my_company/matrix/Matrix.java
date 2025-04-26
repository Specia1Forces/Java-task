package com.my_company.matrix;

import com.my_company.i_matrix.IMatrix;

import java.util.Random;

abstract public class Matrix implements IMatrix {
    protected int row;
    protected int column;

    public abstract void setElement(int row, int column, int value);

    public abstract int getElement(int row, int column);

    public boolean equals(Object o) {
        if (this.hashCode() != o.hashCode())
            return false;
        if (o instanceof Matrix) {
            Matrix a = (Matrix) o;
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
    }

    public IMatrix sum(IMatrix m) {
        IMatrix c = m.getInstance(this.row, this.column);

        if ((this.row != m.getRow()) || (this.column != m.getCol())) {
            throw new RuntimeException("summation is not possible!");
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                // c.array[i][j] = this.array[i][j] + a.array[i][j];
                c.setElement(i, j, this.getElement(i, j) + m.getElement(i, j));
            }
        }

        return c;
    };

    public IMatrix product(IMatrix m) {
        IMatrix c = m.getInstance(this.row, this.column);

        if (this.column != m.getRow()) {
            throw new RuntimeException("Multiplication is impossible!");
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < m.getCol(); j++) {

                for (int k = 0; k < this.column; k++) {
                    c.setElement(i, j, c.getElement(i, j) + this.getElement(i, k) * m.getElement(k, j));
                }
            }
        }
        return c;
    };

    public final void randomize(int limit) {
        Random rand = new Random();

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.setElement(i, j, rand.nextInt(limit));
            }
        }
    }

    public abstract IMatrix getInstance(int row, int column);

    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                strBuffer.append(this.getElement(i, j));
                strBuffer.append(" ");
            }
            strBuffer.append("\n");
        }
        return strBuffer.toString();
    };

    public int hashCode() {
        final int prime = 37;
        int result = 17;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                result = prime * result + this.getElement(i, j);
            }
        }
        return result;
    };




    public final int getRow() {
        return this.row;
    }

    public final int getCol() {
        return this.column;
    }
}