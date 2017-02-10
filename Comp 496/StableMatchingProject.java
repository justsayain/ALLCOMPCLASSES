//Steve Delgado
//Project 1
//Stable Matching Algorithm Brute Force
import java.io.*;
import java.util.*;

public class StableMatchingProject{
       //tried to make this method as O(n) to print instead of O(n^2)
  public static void printMatrix(int[][] givenMatrix, int n){
    for(int i= 0; i<(givenMatrix.length * givenMatrix[0].length);i++){
      System.out.print(givenMatrix[i/givenMatrix[0].length][i % givenMatrix[0].length] + " ");
      if(i%givenMatrix[0].length == givenMatrix[0].length-1){
        System.out.println();
      }
    }
  }
   //iterates through the matrix
  public static int [][] getPref(int  n, Scanner sc){
    int[][] pref = new int[n][n];
    for(int i = 0; i<n; i++){
      for(int j=0; j<n; j++){
        pref[i][j] = sc.nextInt();
      }
    }
    return pref;
  }
   //brute force algorithm calls permute
   public static void bruteForce(int [][] men, int [][] women,int size){
     int start=0;
     int [] match = new int[size];
     for(int i = 0; i<size; i++){
       match[i]=i;
     }
     permute(match,start , size, men, women);
   }


   //http://stackoverflow.com/questions/2920315/permutation-of-array
   //O(n*n!)
  static void permute(int[] arr, int k,int size,int[][]men,int[][]women){
          for(int i = k; i < arr.length; i++){
            swap(arr,k,i);
            permute(arr, k+1,size,men,women);
            swap(arr, k, i);
          }
          if (k == arr.length -1){
              if(isStable(men, women, size,arr))
                printSol(arr, size);
            //  else
                //System.out.println("no solution");
          }
      }

    //swap array elemeents for permutation
   public static void swap(int [] arr, int a, int b){
     int temp;
       temp = arr[b];
       arr[b]= arr[a];
       arr[a]=temp;
   }

  public static boolean isStable (int men[][], int women[][], int n, int arr[]){
      int womDepends[] = new int[n];
      for (int i = 0; i < n; i++)
          for (int j = 0; j < n; j++)
              if (arr[j] == i)
                  womDepends[i] = j;
      for (int i = 0; i < n; i++)
          for (int j = 0; men[i][j] != arr[i]; j++)
              if (i == checkWomPref(i, womDepends[men[i][j]], women[men[i][j]], n))
                  return false;//instability
      return true;
  }
  //check whether the 1st male trumps the second
  public static int checkWomPref(int m1, int m2, int woman[], int size){
      for (int i = 0; i < size; i++){
          if (woman[i] == m1)
              return m1;
          if (woman[i] == m2)
              return m2;
      }
      return -1;
  }
   //prints solution
  public static void printSol(int[]arr, int size){
    System.out.print("[");
    for(int i =0;i<size;i++){
      if(i != 0){
          System.out.print(", ");
      }
      System.out.print("("+i+","+arr[i]+")");
    }
    System.out.println("]");
  }
  //calls GSStable class to perform gayle shapley's algorithm
  //not finished working completeley
  public static void gsStable(int [][] males,int [][]females, int size){

    int[] m = new int [size];
    for(int i=0;i<size;i++){
      m[i]=i;
    }
    int[] w = new int [size];
    for(int i=0;i<size;i++){
      w[i]=i;
    }
    GSStable solution = new GSStable(m,w,males,females);

}

public static void main(String args[]){
    Scanner file = new Scanner(System.in);
    try{
      file = new Scanner(new File(args[0]));
      while(file.hasNext()){

        int size = file.nextInt();
        System.out.println(size);
        int manPref[][] = getPref(size,file);
        int womPref[][] = getPref(size,file);
        //printMatrix(manPref, size);
        //System.out.println();
        //printMatrix(womPref, size);
        //long startBF=System.nanoTime();
        //System.out.println("Brute Force");
        //bruteForce(manPref,womPref,size);
        //long endBF =System.nanoTime();
        //System.out.println("Runtime: " + (endBF - startBF)/1000000 + "ms");
        long startGS = System.nanoTime();
        System.out.println("GS Stable");
        gsStable(manPref,womPref,size);
        System.out.println("Runtime: " + (System.nanoTime() - startGS)/1000000 + "ms");
        //System.out.println();
      }
    }
    catch(IOException e){
      //e.errorMessage();
    }




  }




  //public
}
