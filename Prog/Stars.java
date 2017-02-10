/* Programmer: Steve Delgado
Comp 108 Tues/Thur 8-9:15
Project 11 Part1
*/

   public class Stars{
      public static void main(String[]args){
         for(int i=1;i<=5;i++){
            for(int j=1;j<=5-i;j++){
               System.out.print(" ");
            }
            for(int k=1;k<=i ;k++){
               System.out.print("*");
            }
            System.out.println();
         }
      }}

