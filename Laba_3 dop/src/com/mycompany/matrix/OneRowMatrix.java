package com.mycompany.matrix;
import com.mycompany.summatrixexception.*;

//import laba_4.SortedIntegerList;

//import java.util.Iterator;

import com.mycompany.productmatrixexception.*;
public class OneRowMatrix{
    int row = 0;
    int column = 0;
    int array[][];

    public OneRowMatrix (int row, int column){
       
        if(row < 0 || column < 0) throw new  RuntimeException("Went beyond the matrix");
        this.row = row;
        this.column = column;
        array = new int[1][]; 
        array[0] = new int[column];
    }
    
    public  OneRowMatrix sum (OneRowMatrix a){
    	OneRowMatrix c = new OneRowMatrix(this.row, this.column);
        if ((this.row != a.row) ||(this.column != a.column)) throw new  SumMatrixException("Multiplication is impossible!");
            for (int j = 0; j < this.column; j++){
                c.array[0][j] = this.array[0][j] + a.array[0][j];            
                System.out.println( c.array[0][j]);
        }
        return c; 
     }
     public OneRowMatrix product(OneRowMatrix a){
    	 OneRowMatrix c = new OneRowMatrix(this.row, this.column);
         if (this.column != a.row) throw new  ProductMatrixException("Multiplication is impossible!");
         //(int i = 0; i < this.row; i++){
             for(int j = 0; j < a.column; j++){
                 for(int k = 0; k < this.column; k++){
                     c.array[0][j] += (this.array[0][k] * a.array[0][j]);
                 }
            // }
          }
          return c;
    }
     
     public void setElement(int row, int column, int value){
         if((row < 0 || column < 0) && (row > this.row)&& (column > this.row)) throw new  RuntimeException("Went beyond the matrix!");
	 this.array[0][column] = value;
     }

     public int getElement(int row, int column){
	 if((row < 0 || column < 0) && (row > this.row)&&(column > this.row)) throw new  RuntimeException("Went beyond the matrix!");
         return array[0][column];
     }
     
     public String toString(){
         StringBuffer strBuffer = new StringBuffer();
         for(int i = 0; i < this.row; i++){
             for(int j = 0; j < this.column; j++){
	         strBuffer.append(array[0][j]);
	         strBuffer.append(" ");
             }
         strBuffer.append("\n");
        }
        return strBuffer.toString();  
     }       
     
     public boolean equals ( Object o ) {
    	 if(this.hashCode() != o.hashCode())
 			return false;
    	 if ( o instanceof OneRowMatrix ) {
    		 OneRowMatrix a = (OneRowMatrix)o;
    		 if ((this.row != a.row) ||(this.column != a.column) ) {
    			 return false;   
    		 }
    		 for (int i = 0; i<this.column; i++){
                 if (this.array[0][i] != a.array[0][i]){
                	 return false;
                 }
    		 }
    		 return true;
    	 }
    	 else 
    		 return false;
     }
}
//ProductMatrixException


