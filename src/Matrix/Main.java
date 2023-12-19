/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Matrix;

/**
 *
 * @author ekaterina
 */
import java.util.Scanner;
public class Main {

    /**
     * @param args the command line arguments
     */
        // TODO code application logic here
    
    public static void main(String[] args){
     Matrix m = new Matrix();
     m.inputMatrix();
//     m.outputMatrix();
//     Matrix b=new Matrix();
//     b.inputMatrix();
//     m.matrixMultiplication(b);
 //    System.out.println(m.matrixRang());
//     m.transpMatrix();
     Matrix sv=new Matrix();
     sv.inputMatrix();
     m.solve2(sv);
     m.pow(5);
     
        
    }
}