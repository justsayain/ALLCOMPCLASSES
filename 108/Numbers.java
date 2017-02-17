/* Programmer: Steve Delgado
Comp 108 Tues/Thur 8-9:15
Project 11 Part2
*/

   import java.util.Scanner;
   public class Numbers{
      public static void main(String[]args){
         Scanner input = new Scanner(System.in);
         System.out.print("Enter numbers: ");
         int num=input.nextInt();
         int max=num;
         int count=1;
         while (num!=0){
            if (num>max){
               max=num;
               count=1;}
            else if (num==max)
               count++;
            num=input.nextInt();
         }
      
         System.out.println("largest number is: "+max);
         System.out.print("The occurrence count of the largest number is "+count);
      }}
