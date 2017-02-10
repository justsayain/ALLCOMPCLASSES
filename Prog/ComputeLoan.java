 /*Programmer: Jessica Osorio  COMP 108 TU-TH 8-9:15AM
 Project #: 10 Write a program that lets the user enter the
 loan amount, number of years, and the annual interest rate
 and displays the amortization schedule for the loan.
 */
	
	import java.util.Scanner;

   public class ComputeLoan{
      public static void main(String[] args){ 
      
      //Create a scanner.
         Scanner input = new Scanner(System.in);
      	
      //Enter loan amount.
         System.out.print("Enter loan amount, e.g., 120000.95: ");
         double loanAmount = input.nextDouble();
      
      //Enter number of years.
         System.out.print("Enter number of years as an integer, e.g., 5: ");
         int numberOfYears = input.nextInt();
      
      //Enter annual interest rate in percentage.
         System.out.print("Enter annual interest rate, e.g., 7.25%: ");
         double annualInterestRate = input.nextDouble();
      
      //Obatin monthly interest rate.
         double monthlyInterestRate = annualInterestRate / 1200;
      
      //Calculate Payment.
         double monthlyPayment = loanAmount*monthlyInterestRate / (1
            -1 / Math.pow(1 + monthlyInterestRate, numberOfYears*12));
         double totalPayment = monthlyPayment*numberOfYears*12;
      
         System.out.println("Monthly payment: $" +
            (int)(monthlyPayment*100)/100.0);
         System.out.println("Total payments: $" +
            (int)(totalPayment*100)/100.0);
      	
			System.out.println("\nPayment #     Interest    Principal    Balance");
         double balance = 10000;
         for(int p = 1; p <= 12; p++){
            double interestRate = monthlyInterestRate * balance;
				double principal = monthlyPayment - interestRate;
				balance = balance - principal;
				System.out.printf("%-2d %12s %5.2f %4s %1.2f %5s %8.2f \n",p,"$",interestRate,"$",principal,"$",balance);
         }
      }