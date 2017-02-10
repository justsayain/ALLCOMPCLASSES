import java.util.*;
public class simpleSubtracion{
   public static void main(String[]args){
      System.out.println("Enter sprints: ");
      Scanner input = new Scanner(System.in);
      int sprints = input.nextInt();
      System.out.println("Enter start: ");
      Scanner sprint = new Scanner(System.in);
      double start =sprint.nextDouble();
      double [] sprintBurn = new double [sprints-1];
      double subtract=start/sprints;
      sprintBurn[0]=start;
      for(int i =1;i<sprintBurn.length;i++){
         start-=subtract;
         sprintBurn[i]=start;
      }
      printArray(sprintBurn);
   }
   public static void printArray(double []arr){
      for(int i=0;i<arr.length;i++)
         System.out.print((int)arr[i]+", ");       
      System.out.println();
   }

}