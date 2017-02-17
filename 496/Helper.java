import java.io.*;
import java.util.*;

public class Helper{
    public static void main(String args[]){
    Scanner file = new Scanner(System.in);

    try{
      file = new Scanner(new File(args[0]));
    }
    catch(IOException e){
      //e.errorMessage();
    }
    while(file.hasNext()){
      int size = file.nextInt();
      System.out.println(size);
      int manPref[][] = getPref(size,file);
      int womPref[][] = getPref(size,file);
      printMatrix(manPref, size);
      System.out.println();
      printMatrix(womPref, size);
      System.out.println("Brute Force Solution:");
      bruteForce(manPref,womPref,size);
      System.out.println("GS Stable");
      gsStable(manPref,womPref,size);
      System.out.println();

    }

  }//end main

  public static void printMatrix(int[][] givenMatrix, int n){
    for(int i= 0; i<(givenMatrix.length * givenMatrix[0].length);i++){
      System.out.print(givenMatrix[i/givenMatrix[0].length][i % givenMatrix[0].length] + " ");
      if(i%givenMatrix[0].length == givenMatrix[0].length-1){
        System.out.println();
      }
    }
  }

  public static int [][] getPref(int  n, Scanner sc){
    int[][] pref = new int[n][n];
    for(int i = 0; i<n; i++){
      for(int j=0; j<n; j++){
        pref[i][j] = sc.nextInt();
      }
    }
    return pref;
  }

   public static void bruteForce(int [][] men, int [][] women,int size){
     int [] match = new int[size];
     for(int i = 0; i<size; i++){
       match[i]=i;
     }
     permute(match, 0, size, men, women);
   }
   //http://stackoverflow.com/questions/2920315/permutation-of-array
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
   public static void swap(int [] arr, int a, int b){
     int temp;
       temp = arr[b];
       arr[b]= arr[a];
       arr[a]=temp;
   }

  public static boolean isStable (int men[][], int women[][], int n, int matches[]){
      int womenMatches[] = new int[n];
      for (int i = 0; i < n; i++)
          for (int j = 0; j < n; j++)
              if (matches[j] == i)
                  womenMatches[i] = j;
      for (int i = 0; i < n; i++)
          for (int j = 0; men[i][j] != matches[i]; j++)
              if (i == checkFemalePreference(i, womenMatches[men[i][j]], women[men[i][j]], n))    //if female preferes i then instability
                  return false;
      return true;
  }//isUnstable
  public static int checkFemalePreference(int male1, int male2, int woman[], int size){
      for (int i = 0; i < size; i++){
          if (woman[i] == male1)
              return male1;
          if (woman[i] == male2)
              return male2;
      }
      return -1;
  }//checkFemalePreference
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





  //public
}
