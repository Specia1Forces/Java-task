
import java.lang.String;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("Enter dig:");
        int n = console.nextInt();
        Matrix m1 = new Matrix(n,n);
        Matrix m2 = new Matrix(n,n);
        m1.setElement(0,1,1);
        m1.setElement(1,0,1);
        m1.setElement(1,1,0);
        System.out.println(m1.toString());
        m2.setElement(0,1,1);
        m2.setElement(1,0,1);
        m2.setElement(1,1,0);
        System.out.println(m2.toString());
        System.out.println("----");
        for(int i = 0; i < 10; i++){
            System.out.println(m2.toString());
            m2 = m2.product(m1);
        }
    }
}

class Matrix{
    int row = 0;
    int column = 0;
    int array[][];

    public Matrix( int row, int column ){
        array = new int[row][];
        for (int i = 0; i < row; i++){
            array[i] = new int[column];
        }
        this.row = row;
        this.column = column;
        for (int i = 0; i<this.row; i++){
            for (int j = 0; j<this.column; j++){
                this.array[i][j] = 0 ;
            }
        }
        for (int i = 0; i < row; i++) {
            array[i][i]=1;
        }
    }

    public  Matrix sum(Matrix a){
        Matrix c = new Matrix(this.row,this.column);
        for (int i = 0; i<this.row; i++){
            for (int j = 0; j<this.column; j++){
                c.array[i][j] = this.array[i][j] + a.array[i][j];
            }
        }
        return c;
    }
    public Matrix product(Matrix a){
        Matrix c = new Matrix(this.row,this.column);
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < a.column; j++){
                c.array[i][j] = 0;
                for(int k = 0; k < this.column; k++)
                    c.array[i][j] += (this.array[i][k] * a.array[k][j]);
            }
        }
        return c;
    }

    public void setElement(int row, int column, int value){
        this.array[row][column] = value;
    }
    public int getElement(int row, int column){
        return array[row][column];
    }

    public String toString(){
        StringBuffer strBuffer = new StringBuffer();
        //String s = new String();
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.column; j++){
                strBuffer.append(array[i][j]);
                strBuffer.append(" ");
            }
            strBuffer.append("\n");
        }
        return strBuffer.toString();
    }

}