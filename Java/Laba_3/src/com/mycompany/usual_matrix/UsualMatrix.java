package com.mycompany.usual_matrix;


import com.mycompany.my_exception.MyException;

public class UsualMatrix {
    protected int row = 0;
    protected int column = 0;
    protected int array[][];

    public UsualMatrix(int row, int column) {
        try {
            if (row < 0 || column < 0) throw new MyException("Went beyond the matrix");
            array = new int[row][];
            for (int i = 0; i < row; i++) {
                array[i] = new int[column];
            }
            this.row = row;
            this.column = column;
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.column; j++) {
                    this.array[i][j] = 0;
                }
            }
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public UsualMatrix sum(UsualMatrix a) {
        UsualMatrix c = new UsualMatrix(this.row, this.column);
        try {
            if ((this.row != a.row) || (this.column != a.column)) {
                throw new MyException("summation is not possible!");
            }
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.column; j++) {
                    c.array[i][j] = this.array[i][j] + a.array[i][j];
                }
            }

        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    public UsualMatrix product(UsualMatrix a) {

        UsualMatrix c = new UsualMatrix(this.row, a.column);
        try {

            if (this.column != a.row) {
                throw new MyException("Multiplication is impossible!");
            }
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < a.column; j++) {
                    c.array[i][j] = 0;
                    for (int k = 0; k < this.column; k++) {
                        c.array[i][j] += (this.array[i][k] * a.array[k][j]);
                    }
                }
            }
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        return c;

    }

    public void setElement(int row, int column, int value) {
        try {
            if (row < 0 || column < 0) throw new MyException("Went beyond the matrix!");
            this.array[row][column] = value;
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getElement(int row, int column) {
        try {
            if (row < 0 || column < 0) throw new MyException("Went beyond the matrix!");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        return array[row][column];
    }

    public boolean equals(UsualMatrix a) {
        if ((this.row != a.row) || (this.column != a.column)) {
            return false;
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (this.array[i][j] != a.array[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        final int prime = 37;
        int result = 17;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                result = prime * result + array[i][j];
            }
        }
        return result;
    }

    public String toString() {
        StringBuffer strBuffer = new StringBuffer();
        //String s = new String();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                strBuffer.append(array[i][j]);
                strBuffer.append(" ");
            }
            strBuffer.append("\n");
        }
        return strBuffer.toString();
    }

}
