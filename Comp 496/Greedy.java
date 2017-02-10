//Steve Delgado
//Project 2
//Greedy Class that contains Greedy Algorithm and MST using Prim's algorithm
import java.util.*;

public class Greedy{
   private int size;
   public double [][] distanceMatrix;
   private int currentDistance;
   private int [] end;
   private boolean [] visited;
   private double[]x;
   private double[]y;
   private int count;
   private int currentCity=0;
   private int startCity=0;
   private int tempCity=0;
   public double minBrute=0.0;
   public int [] bruteArr;
   
   //Constructor
   public Greedy(double []x,double[]y){
      size = x.length;
      count =0;
      this.x =x;
      this.y=y;
      visited = new boolean[size];
      end = new int[size];
   }
   public void findGreedy(){
      end =new int[size+1];
      end[0]=0;
      visited[0]=true;
      count++;
      double totalDistance=0;
      while(count<size){
         totalDistance+=closest(x,y,currentCity,Integer.MAX_VALUE);
         end[count]=currentCity;
         visited[currentCity]=true;
         count++;
         //System.out.println(totalDistance);
      }
      totalDistance+=distance(x,y,currentCity,0);
      currentCity=0;
      printGreedy(totalDistance,end);
   }
   private void printGreedy(double finalResult,int [] array){
      System.out.println("Best Greedy Tour: "+size);
      System.out.println("Min Cost: "+finalResult);
      printArray(array);
      printCity(array);
   }
      public void bruteForce(){
      int start =0;
      int [] permute= new int[size];
      for(int i =0;i<size;i++){
         permute[i]=i;
      }
      permute(permute,start+1,x,y);
      
   }
   public void printBrute(){
      System.out.println("Optimal Tour: "+size);
      System.out.println("Min Cost: "+minBrute);
      printArray(bruteArr);
      printCity(bruteArr);
      
   }
   public void permute(int[]arr,int k,double []x,double[]y){
      int i;
      int start=0;
      double result=0;
      //minBrute=Integer.MAX_VALUE;
      double temp;
      //double minResult;
      for(i = k; i < arr.length; i++){
         swapCities(arr,k,i);
         permute(arr, k+1,x,y);
         swapCities(arr, k, i);
      }
      if(k==arr.length-1){
         minBrute=bruteClosest(x,y,arr,minBrute);
         //System.out.println("minBrute "+minBrute);
      }
      
      //System.out.println("minimum: "+minBrute);
   }
   public double bruteClosest(double []x,double[]y,int []arr,double finalResult){
      //bruteArr = new int [size];
      double result=0.0;
      int i;
      for(i=0;i<size-1;i++){
         result+=distance(x,y,arr[i],arr[i+1]);
      }
      result+=distance(x,y,arr[i],0);
      if(!(finalResult==0)&&finalResult<result){
         result = finalResult;
         copyArr(arr);
      }
      //System.out.println("result : "+result);
      return result;
   }
   
   public void copyArr(int []arr){
      bruteArr= new int[size+1];
      for(int i=0;i<size;i++){
         bruteArr[i]=arr[i];
      }
   }
   public void swapCities(int []arr, int a, int b){
      int temp;
      temp = arr[b];
      arr[b]= arr[a];
      arr[a]= temp;
   }
   public double closest(double []x,double[] y,int city,double finalResult){
      for(int j=0;j<size;j++){
         if(!visited[j]){
            double result1 = distance(x,y,city,j);
            if(finalResult<result1){
               finalResult = finalResult;
               //currentCity=city;
            }
            else{
               finalResult = result1;
               currentCity=j;
               tempCity=city;
            }
         }
      }
      return finalResult; 
   }
   public double distance(double []x,double[] y,int city1, int city2){
      double result;
      result = Math.sqrt((double)Math.pow((x[city2]-x[city1]),2)+Math.pow((y[city2]-y[city1]),2));
      return result;
   }
   public void printMatrix(double[][] givenMatrix){
      for(int i= 0; i<(givenMatrix.length * givenMatrix[0].length);i++){
         System.out.print(givenMatrix[i/givenMatrix[0].length][i % givenMatrix[0].length] + " ");
         if(i%givenMatrix[0].length == givenMatrix[0].length-1){
            System.out.println();
         }
      }
   }
   public void printArray(int []arr){
      for(int i=0;i<arr.length;i++)
         System.out.print(arr[i]+" ");       
      System.out.println();
   }
   public void printCity(int []arr){
      for(int i=0;i<arr.length;i++)
         System.out.print((char)('A'+arr[i])+" ");
         
      System.out.println();
   }

   public void setDistanceMatrix(double [][] distanceMatrix){
      double finalDistance;
      for(int i=0;i<size;i++){
         for(int j=0;j<size;j++){
            finalDistance=distance(x,y,i,j);
            distanceMatrix[i][j]=finalDistance;
            //System.out.println(finalDistance);
         }
      }
   }
   
   public void findMST(double [][] dMatrix){
      
      int count=0;
      LinkedList<Integer> distanceQ =  new LinkedList<Integer>();
      visited = new boolean[size];
      int [] endMST = new int [size+1];
      int [] parent = new int [size];
      //Arrays.fill(endMST,-1);
      double [][] arrMST = new double[size][size];
      double minDistance = Integer.MAX_VALUE;
      double totalDistance =0.0;
      for(int i=0;i<size;i++){
         for(int j=0;j<size;j++){
            arrMST[i][j]=Integer.MAX_VALUE;   
            //arrMST[i][j]=0;
         }
      }
      distanceQ.add(0);
      //printMatrix(arrMST);
      visited[0]=true;
      endMST[0]=0;
      parent[0]=-1;
      count++;
      tempCity=0;
      while(count<size){
         minDistance = Integer.MAX_VALUE;
         while(!distanceQ.isEmpty()){
            minDistance=closest(x,y,distanceQ.poll(),minDistance);
         }
         endMST[count]=currentCity;
         parent[currentCity]=tempCity;
         visited[currentCity]=true;
         arrMST[tempCity][currentCity]=minDistance;
         arrMST[currentCity][tempCity]=minDistance;
         totalDistance+=minDistance;
         //System.out.println(totalDistance);
         count++;
         for(int i=0;i<size&&!(endMST[i]==-1);i++){
            distanceQ.addFirst(endMST[i]);
         }
      }
      totalDistance+=distance(x,y,currentCity,0);
      arrMST[currentCity][0]=distance(x,y,currentCity,0);
      arrMST[0][currentCity]=distance(x,y,currentCity,0);
      printMST(totalDistance,endMST,arrMST);
   } 
      public void printMST(double n,int []arr,double [][] Matrix){
         System.out.println("MST Tour: "+size);
         System.out.println("Min Cost: "+n);
         printArray(arr);
         printCity(arr);
         System.out.println("MST: ");
         printMatrix(Matrix);
      }
}
