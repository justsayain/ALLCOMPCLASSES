/* Programmer: Steve Delgado
Comp 110/110L Spring 2013
Project 2: Part 2
Mortgage Interest */

import java.util.*;
public class Mortgage{
	public static void main(String[]args){
		// create Scanner
		Scanner input= new Scanner(System.in);
		
		//create variables
		double principal, monthlyRate, annualRate ,years, months, payment;
		
		//obtain inputs
		System.out.print("Enter principal: ");
		principal= input.nextDouble();
		System.out.print("Enter annual interest rate: ");
		annualRate= input.nextDouble();
		System.out.print("Enter number of years: ");
		years= input.nextDouble();
		//compute and obtain results
		monthlyRate= annualRate/12;
		months= years*12;
		payment = (principal*monthlyRate)/(1-Math.pow(1+monthlyRate,-months));
		//output results
		System.out.println("Monthly Payments: " + payment);
}
}