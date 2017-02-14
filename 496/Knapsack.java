//Steve Delgado
//Project 3
//Knapsack class that contains Brute Force and Dynamic 
//methods that Solve Knapsack using Brute Force and Dynamic Programming
import java.util.*;
import java.lang.*;

public class Knapsack{
   private int items;
   private int []weights;
   private int []value;
   private int capacity;
   private boolean [][]subset;
   private int []sub;
   private int index=0;
   private int [][]optMatrix;
   ArrayList<List<Integer>> allSubset = new ArrayList<List<Integer>>();
   ArrayList<List<Integer>> storedSubset = new ArrayList<List<Integer>>();
   private int maxV;
   private int maxW;

   
   //constructor
   public Knapsack(int a,int b, int[]c,int[]d){
      items=a;
      weights=new int[a+1];
      weights=c;
      value= new int [a+1];
      value=d;
      capacity=b;
      subset=new boolean[a+1][capacity+1];  
      sub = new int[a]; 
      optMatrix=new int[a+1][capacity+1];
   }
   public void makeSub(int n){
      ArrayList<Integer>set = new ArrayList<Integer>();
      for(int i =1;i<=n;i++){
         set.add(i);
      }
      getSubSet(set,0);
   }
   //http://stackoverflow.com/questions/4640034/calculating-all-of-the-subsets-of-a-set-of-numbers
   //This is one of the comments and it is based on the power set
   //@Author:Sherin Syriac
   public void getSubSet(ArrayList<Integer> set, int index) {
      if (set.size() == index) {
         ArrayList<Integer> temp = new ArrayList<Integer>();
         allSubset.add(temp);
      } 
      else {
         getSubSet(set, index + 1);
         ArrayList<List<Integer>> tempAllSubsets = new ArrayList<List<Integer>>();
         for (List subset : allSubset) {
            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.addAll(subset);
            newList.add(set.get(index));
            tempAllSubsets.add(newList);
         }
      
         allSubset.addAll(tempAllSubsets);
      }
   
   }

   public void brute(){
      makeSub(items);
      //printAllSets();
      solve();
      printBrute();
       //return solve(capacity,weights,value,items,allSubset);      
   }
    
   public void solve(){ //T(n)=O(2^n*n)
      for(int i=1;i<Math.pow(2,items);i++){ //O(2^n)
         int j=items;
         int tempW=0;
         int tempV=0;
         int index=0;
         int s=allSubset.get(i).size();
         while(index<s){                    //O(n)
            tempV+=value[allSubset.get(i).get(index)-1];
            tempW+=weights[allSubset.get(i).get(index)-1];
            if(tempV>maxV&&tempW<=capacity){
               maxV=tempV;
               storedSubset.clear();
               storedSubset.add(allSubset.get(i));
            }
            else if(tempV==maxV&&tempW<=capacity){
               storedSubset.add(allSubset.get(i));
            
            }
            index++;
         }
            
      }
   }
   public void printBrute(){
      System.out.println("Brute Force Optimal Solutions(all subset)");
      
      for(int i=0;i<storedSubset.size()&&(storedSubset.get(i)!=null);i++){
         System.out.println("Subset of items "+storedSubset.get(i));
         System.out.println("Subset Weight: "+getSubW(i));    
         System.out.println("Subset Value: "+maxV); //can do a get subMax but no reason as the max value is always the same
      }
   }
   public int getSubW(int x){
      int total=0;
      for(int j=0;j<storedSubset.get(x).size();j++){
         total+=weights[storedSubset.get(x).get(j)-1];        
      }
      return total;
   }
   public void printAllSets(){
      for (List<Integer> list : allSubset) {
         System.out.print("{");
         for (Integer element : list) {
            System.out.print(element+" ");
         }
         System.out.println("}");
      }
   }
   public int max(int x, int y,int a,int b,boolean [][]matrix){
      if(x<y){
         matrix[a][b]=true;
         return y;
      }
      else
         return x;
   }  
   public void dynamic(){
      System.out.println("Dynamic Programming Optimal Solution (One Solution):");
      solveDynamic(items,weights,value,capacity,subset);
   }
   public void solveDynamic(int size, int []weights,int []v,int capacity,boolean[][]matrix){
      int i,w;
      boolean [][] save = new boolean [size][capacity+1];
      for(i=0;i<=size;i++)
         optMatrix[i][0]=0;
      for(i=1;i<=size;i++){
         for(w=0;w<=capacity;w++){
            if(i==0||w==0)
               optMatrix[i][w]=0;
            else if(weights[i-1]>w)
               optMatrix[i][w]=optMatrix[i-1][w];
            else
               optMatrix[i][w]=max(optMatrix[i-1][w],v[i-1]+optMatrix[i-1][w-weights[i-1]],i,w,matrix);
         }
      }
      int takeAway=capacity;
      int totalWeight=0;
      for(i=size;i>=1;i--){
         if(subset[i][takeAway]==true){
            save(i,index);
            totalWeight+=weights[i-1];
            takeAway-=weights[i-1];
         }
      }
      reverse(sub);
      System.out.println("subset weight: "+totalWeight);
      //printMatrix(knap);
      System.out.println("subset value: "+optMatrix[size][capacity]);
      //printSub(subset); 
   }
   public void save(int a,int n){
      sub[n]=a;   
      index++;
      
   }
   public void reverse(int []arr){
      System.out.print("Subset of items {");
      for(int i=arr.length-1;i>=1;i--){
         if(arr[i]!=0)
            System.out.print(sub[i]+", ");
      }
      System.out.println(arr[0]+"}");
   }
   public void printMatrix(){
      for(int i= 0; i<(optMatrix.length * optMatrix[0].length);i++){
         System.out.print(optMatrix[i/optMatrix[0].length][i % optMatrix[0].length] + " ");
         if(i%optMatrix[0].length == optMatrix[0].length-1){
            System.out.println();
         }
      }
   }
   public void printSub(boolean[][] givenMatrix){
      for(int i= 0; i<(givenMatrix.length * givenMatrix[0].length);i++){
         System.out.print(givenMatrix[i/givenMatrix[0].length][i % givenMatrix[0].length] + " ");
         if(i%givenMatrix[0].length == givenMatrix[0].length-1){
            System.out.println();
         }
      }
   }
}