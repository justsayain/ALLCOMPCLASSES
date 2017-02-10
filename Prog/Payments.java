/* Programmer: Steve Delgado
Comp 108 Tues/Thur 8-9:15
Project 10
*/
   import java.util.Scanner;
   public class Payments{
      public static void main(String[]args){
         Scanner input=new Scanner(System.in);
         System.out.print("Loan Amount: $");
         double loan=input.nextDouble();
         System.out.print("Number of years: ");
         int years=input.nextInt();
         System.out.print("Annual Interest Rate: ");
         double annualRate=input.nextDouble();
      
      
         double monthlyRate= annualRate/1200;
         double monthlyPayment= loan *monthlyRate/(1-1/Math.pow(1+monthlyRate,years*12));
         System.out.println("Monthly Payment: $"+(int)(monthlyPayment*100)/100.0);
         double totalPayment = monthlyPayment*years*12;
         System.out.printf("Total Payment: $%8.2f\n",totalPayment);
      
         System.out.println("Payment#     Interest    Principal     Balance");
         double monthlyInterest=0,principal=0,balance=0;
         for(int i=1;i<=12;i++){
            monthlyInterest=monthlyRate*loan;
            principal= monthlyPayment-monthlyInterest;
            balance= loan-principal;
            loan=balance;
            System.out.printf("%-2d %12s %5.2f %4s %1.2f %5s %8.2f \n",i,"$",monthlyInterest,"$",principal,"$",balance);
         }
      }}
