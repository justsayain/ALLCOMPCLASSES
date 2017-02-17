//Steve Delgado
//Project 1
//GSStable class to run	algorithm
import java.util.*;

public class GSStable{

   private	int N, engagedCount;
   private	int[][] menPref;
   private	int[][] womenPref;
   private	int[]	men;
   private	int[]	women;
   private	int[]	partner;
   private	boolean[] menEngaged;



		 // Constructor 
   public GSStable(int[] m, int[] w, int[][] mp, int[][] wp){
      N =	mp.length;
      engagedCount	= 0;
      men	= m;
      women = w;
      menPref =	mp;
      womenPref	= wp;
      menEngaged =	new boolean[N];
      partner =	new int[N];
      Arrays.fill(partner,-1);
      solveGS();
   }

      //solves matching using Gale Shapley method
   private	void solveGS(){
      while (engagedCount <	N){
         int free;
         for (free =	0;	free < N; free++)
            if (!menEngaged[free])  //checks to see if men are free
               break;
                       
         for (int	i = 0; i	< N && !menEngaged[free]; i++){
            int index = menPref[free][i];    //get women men prefer
            if (partner[free] == -1 && !isItThere(partner, menPref[free][i])){   //men is free and women is not engaged to another man
               partner[free] = menPref[free][i];   //match male with female
               menEngaged[free] =	true;    //men is no longer free
               engagedCount++;
            }
            else{
               int	currentPartner	= getIndex(partner,index); //old partner
                       //int old = getIndex(partner,currentPartner);
               int first = getDoubleIndex(womenPref,index,currentPartner); //old man partner
               int second = getDoubleIndex(womenPref,index, free);   //new man partner
               if (first<second){   //women prefers old partner to new partner
                  menEngaged[free]=false;
                  partner[free]=-1;
               }
               else{
                  menEngaged[currentPartner]=false;
                  menEngaged[free]=true;
                  partner[currentPartner]=-1;
                  partner[free]=index;
                        
               }
            }
         }
      }
      printSol();
   }
   
   public boolean isItThere(int[]arr, int n){
      boolean there=false;
      for(int i=0;i<N;i++){
         if(n == arr[i]){
            there=true;
            break;
         }
      }
      return there;
   }
 
   public int getIndex(int[]arr, int n){
      int there=-1;
      for(int i=0;i<N;i++){
         if(n == arr[i]){
            there=i;
            break;
         }
      }
      return there;
   }    
       
   public int getDoubleIndex(int[][]arr,int index, int n){
      int there=-1;
      for(int i=0;i<N;i++){
         if(n == arr[index][i]){
            there=i;
            break;
         }
      }
      return there;
   }   
   //checks womens preference 
   private boolean checkPref(int [][]pref,int index, int old, int currentPos){
      boolean check = false;
      if(getDoubleIndex(pref,index, old)<getDoubleIndex(pref,index,currentPos))
         check = true;
      else
         check =false;
      return check;
   }

   public void printSol(){
   
      System.out.print("[");
      for(int	i =0;i<N;i++){
         if(i != 0){
            System.out.print(", ");
         }
         System.out.print("("+i+","+partner[i]+")");
      }
      System.out.println("]");
   
   }
		  
}
