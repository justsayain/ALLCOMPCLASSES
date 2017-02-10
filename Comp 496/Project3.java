//Steve Delgado
//Project 3
//Main Class
//Contains only test cases
import java.util.*;

public class Project3{
   public static void main(String[]args){
   
      int n=6;
      int []weights = {2,3,3,4,4,1};
      int W =10;
      int [] values ={1,2,3,3,3,6};
      System.out.println("Test Case 1: \nNumber of Items = "+n+"\nWeight Capacity of Knapsack: "+W);
      Knapsack sack1 = new Knapsack(n,W,weights,values);
      long startBF=System.currentTimeMillis();
      sack1.brute();
      long endBF =System.currentTimeMillis();
      System.out.println("Runtime Brute Force: " + (endBF - startBF) + "ms");
      System.out.println();
      startBF=System.currentTimeMillis();
      sack1.dynamic();
      endBF =System.currentTimeMillis();
      System.out.println("Runtime Dynamic Program: " + (endBF - startBF) + "ms");
      System.out.println("\nOPT MATRIX");
      sack1.printMatrix();
      System.out.println();
   
      int n2 = 10;
      int[] weights2 = {3,4,2,5,6,1,2,7,8,2};
      int W2 = 12;
      int[] values2 = {6,7,4,3,2,6,8,7,9,6};
      System.out.println("Test Case 2: \nNumber of Items = "+n2+"\nWeight Capacity of Knapsack: "+W2);
      Knapsack sack2 = new Knapsack(n2,W2,weights2,values2);
      startBF=System.currentTimeMillis();
      sack2.brute();
      endBF =System.currentTimeMillis();
      System.out.println("Runtime Brute Force: " + (endBF - startBF) + "ms");
      System.out.println();
      startBF=System.currentTimeMillis();
      sack2.dynamic();
      endBF =System.currentTimeMillis();
      System.out.println("Runtime Dynamic Program: " + (endBF - startBF) + "ms");
      System.out.println();
     
      int n3 = 20;
      int[] weights3 = { 2,3,4,2,6,5,3,7,2,4,3,1,5,6,2,1,1,3,4,3}; 
      int W3 = 18;
      int[] values3 = { 2,3,4,1,2,5,3,2,4,6,2,2,1,3,4,5,6,2,1,9}; 
      System.out.println("Test Case 3: \nNumber of Items = "+n3+"\nWeight Capacity of Knapsack: "+W3);      
      Knapsack sack3 = new Knapsack(n3,W3,weights3,values3);
      startBF=System.currentTimeMillis();
      sack3.brute();
      endBF =System.currentTimeMillis();
      System.out.println("Runtime Brute Force: " + (endBF - startBF) + "ms");
      System.out.println();
      startBF=System.currentTimeMillis();
      sack3.dynamic();
      endBF =System.currentTimeMillis();
      System.out.println("Runtime Dynamic Program: " + (endBF - startBF) + "ms");
      System.out.println();
      
      int n4 = 25;
      int[] weights4 = {9,16,12,8,7, 14,7,8,9,14,15,18,20,2,4,5,10,11,3,17 ,15,18,15,9,7 };
      int W4 = 100;
      int[] values4 = {1,7,3,4,5, 5,3,4,6,2, 6,6,4,2,1, 1,2,2,4,5,4,3,2,1,3}; 
      System.out.println("Test Case 4: \nNumber of Items = "+n4+"\nWeight Capacity of Knapsack: "+W4);
      Knapsack sack4 = new Knapsack(n4,W4,weights4,values4);
      // startBF=System.nanoTime();
//       sack4.brute();
//       endBF =System.nanoTime();
//       System.out.println("Runtime Brute Force: " + (endBF - startBF)/1000000 + "ms");
//       System.out.println();
      System.out.println("Brute Force Optimal Solution:");
      System.out.println(
      "Could not run this test case as I got an out of memory error\nsince I saved all subsets, which in retrospect I should not have done,\nbut If I had enough memory to store 2^25 subsets it would work \n"); 
      startBF=System.currentTimeMillis();
      sack4.dynamic();
      endBF =System.currentTimeMillis();
      System.out.println("Runtime Dynamic Program: " + (endBF - startBF) + "ms");      System.out.println();
      
      

   }
   
}