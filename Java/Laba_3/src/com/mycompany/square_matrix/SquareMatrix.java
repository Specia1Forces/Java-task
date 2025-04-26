package com.mycompany.square_matrix;

import com.mycompany.usual_matrix.UsualMatrix;

public class SquareMatrix extends UsualMatrix {


    public SquareMatrix( int column) {
        super(column,column);
        for (int i = 0; i < row; i++) {
            array[i][i]=1;
        }
    }

    @Override
    public  UsualMatrix sum(UsualMatrix a){
        return super.sum(a);
    }

    public boolean equals(SquareMatrix new_mat){
        if(this.hashCode() != new_mat.hashCode())
            return false;
        if(this == new_mat)
            return true;
        if(getClass() != new_mat.getClass() || this.row != new_mat.row)
            return false;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < row; j++){
                if(this.array[i][j] != new_mat.array[i][j])
                    return false;
            }
        }
        return true;
    }
}