//Steve Delgado
//Project 2
//Main Class
import java.util.*;
import java.io.*;
import java.lang.Math;

public class Project2{
   public static void main(String [] args){
      System.out.println("Test Case 1");
      double xArray1[] = {79.0,57.0,24.0,20.0,46.0};
      double yArray1[] = {45.0,18.0,46.0,5.0,27.0};
      System.out.println("n= "+xArray1.length);
      printInfo(xArray1,yArray1);
      System.out.println();
      Greedy greedy= new Greedy(xArray1,yArray1);
      long startBF=System.nanoTime();
      greedy.bruteForce();
      greedy.printBrute();
      long endBF =System.nanoTime();
      System.out.println("Runtime Brute Force: " + (endBF - startBF)/1000000 + "ms");
      System.out.println();
      startBF=System.nanoTime();
      greedy.findGreedy();
      endBF =System.nanoTime();
      System.out.println("Runtime Greedy: " + (endBF - startBF)/1000000 + "ms");
      System.out.println();
      double distanceMatrix[][]=new double [xArray1.length][yArray1.length];
      
      System.out.println();
      startBF=System.nanoTime();
      greedy.findMST(distanceMatrix);
      endBF =System.nanoTime();
      System.out.println("Runtime: " + (endBF - startBF)/1000000 + "ms");
      System.out.println();
      greedy.setDistanceMatrix(distanceMatrix);
      System.out.println("DISTANCE MATRIX: ");
      greedy.printMatrix(distanceMatrix);
      
      //greedy.printMST();
      System.out.println();
      System.out.println("Test Case 2");
      double xArray2[] = {48.0,181.0,26.0,99.0,107.0,54.0,98.0,117.0,119.0,185.0,68.0,136.0};
      double yArray2[] = {42.0,51.0,93.0,85.0,90.0,184.0,118.0,176.0,32.0,72.0,32.0,142.0};
      System.out.println("n = "+xArray2.length);
      printInfo(xArray2,yArray2);
      System.out.println();
      Greedy greedy2= new Greedy(xArray2,yArray2);
      startBF=System.nanoTime();
      greedy2.bruteForce();
      greedy2.printBrute();
      endBF =System.nanoTime();
      System.out.println("Runtime: " + (endBF - startBF)/1000000 + "ms");
      System.out.println();
      startBF=System.nanoTime();
      greedy2.findGreedy();
      endBF =System.nanoTime();
      System.out.println("Runtime: " + (endBF - startBF)/1000000 + "ms");
      System.out.println();
      double distanceMatrix2[][]=new double [xArray2.length][yArray2.length];
      greedy2.setDistanceMatrix(distanceMatrix2);
      startBF=System.nanoTime();
      greedy2.findMST(distanceMatrix2);
      endBF =System.nanoTime();
      System.out.println("Runtime: " + (endBF - startBF)/1000000 + "ms");
      System.out.println();
      System.out.println("DISTANCE MATRIX: ");
      greedy2.printMatrix(distanceMatrix2);
      System.out.println();
    
   }
   
   public static void printInfo(double []arr1, double []arr2){
      for(int i =0;i<arr1.length;i++){
         System.out.println("City "+i+" "+arr1[i]+" "+arr2[i]+" "+(char)('A'+i));
      }
   }
   public static void printArray(int [] arr){
      for(int i=0;i<arr.length;i++){
         System.out.print(" "+arr[i]);
      }
   }
   }

