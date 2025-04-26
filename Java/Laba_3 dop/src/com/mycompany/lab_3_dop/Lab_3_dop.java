package com.mycompany.lab_3_dop;

import com.mycompany.matrix.*;
import java.util.Scanner;

public class Lab_3_dop {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        //int n = console.nextInt();
        OneRowMatrix m1 = new OneRowMatrix(3, 3);
        OneRowMatrix m2 = new OneRowMatrix(3, 3);
        OneRowMatrix m3;
        
  
	try{
		 m1.setElement(2,1,2);
         m1.setElement(2,2,3);
         m1.setElement(0,0,2);
         m2.setElement(2,1,6);
         m2.setElement(2,2,3);
         m2.setElement(0,0,2);
         System.out.println(m1.toString());
         System.out.println("--------------\n");
         System.out.println(m2.toString());
        }
        catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
	// 	try{
      //  m3=m1.sum(m2);
       // System.out.println(m3.toString());
       
      // }
   // catch(SumMatrixException ex){
     //System.out.println(ex.getMessage());
   //}
	System.out.println("--------------\n");
	// try{
	//m3=m1.product(m2);
		//System.out.println(m3.toString());
     //   }
     //   catch(ProductMatrixException ex){
		//       System.out.println(ex.getMessage());
		//   }

    }
}
