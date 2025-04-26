package com.my_company.i_matrix;

public interface IMatrix {

    void setElement(int row, int column, int value);

    int getElement(int row, int column);

    public IMatrix product(IMatrix a);

    public IMatrix sum(IMatrix a);

    public IMatrix getInstance(int row, int column);

    public int getRow();

    public int getCol();

}
