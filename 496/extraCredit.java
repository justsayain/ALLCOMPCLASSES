/*
   Steve Delgado
   Extra Credit
*/
import java.util.*;

public class extraCredit{
   public static void main(String args[]){
      int [][] topical1= {
         {0,1,0,0,1},
         {0,0,0,1,0},
         {0,1,0,1,0},
         {0,0,0,0,1},
         {0,0,0,0,0}
         };
      int [][] topical2 ={
         {0,0,1,1},
         {1,0,1,0},
         {0,0,0,1},
         {0,0,0,0}
         };
      int [][] topical3 ={
         {0,1,1,1},
         {0,0,1,1},
         {0,0,0,1},
         {0,0,0,0}
         };   
      
      int [][] topical10= {
         {0,1,0,0,0,1,0,1,0,0,0},//0
         {0,0,0,0,1,1,0,0,0,0,0},//1
         {0,1,0,0,0,1,0,0,1,0,0},//2
         {0,0,0,0,0,0,0,0,0,0,0},//3
         {0,0,0,0,0,1,0,0,0,0,0},//4
         {0,0,0,0,0,0,0,1,0,0,0},//5
         {0,0,0,1,0,0,0,0,0,0,0},//6
         {0,0,0,1,0,0,1,0,0,0,0},//7
         {1,0,0,0,0,0,1,0,0,0,0},//8
         {0,1,1,0,0,0,0,0,0,0,0},//9
         {0,0,0,0,0,0,0,1,1,1,0} //10
         };
      //printMatrix(topical2);
      System.out.print("1. ");
      runTopical(topical2);
      System.out.print("2. ");
      runTopical(topical1);
      System.out.print("3. ");
      runTopical(topical3);
      System.out.print("4. ");
      runTopical(topical10);
   }
   
   public static void runTopical(int [][]array){
      int [][]copy= new int[array.length][array.length]; //constant
   //       System.arraycopy(
      copy = array.clone();                        //O(n)
      int answer []=new int[array.length];
      int size = array.length;                    
      int start = 0;
      boolean found[] = new boolean[array.length];
   
      while(start<size){   //n*
         int there;
         for(there =0; there<size;there++){ //O(n)
            if(!found[there])
               break;
         }
         for(int i=0;i<size;i++){   //n
            if(checkRoot(copy,i)&&!found[i]){   //checkroot (n)
               answer[start]=i; //constants     
               found[i]=true;   //
               scrub(copy, i);  // scrub method (n)
               start++; //constant
            }
         }
      }
      printArray(answer); //print method
   }
   //Check to see if "node" has no incoming edges
   public static boolean checkRoot(int [][] array, int index){
      for(int i =0;i<array.length;i++){  //O(n)
         if(array[i][index]==1)
            return false;
      }
      return true;
   }
   
   //erase incoming edge from graph
   public static void scrub(int [][] array, int index){
      for(int i= 0; i<array.length;i++){ //O(n)
         array[index][i]=0;
      }
   }
   
   
   //print array of final answer O(n)
   public static void printArray(int[]array){
      System.out.print("{");
      for(int i = 0;i<array.length;i++){
         if(!(i==array.length-1))
            System.out.print((char)(array[i]+'a')+ ",");
         else
            System.out.print((char)(array[i]+'a'));
      }
      System.out.println("}");
   }
}
               