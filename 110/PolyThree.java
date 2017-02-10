/* Programmer: Steve Delgado
Comp 110/110L Spring 2013 Mon/Wed 8-11am
Project 3: Part 3
Polygon Evaluator Version 3 */

import java.util.*;
public class PolyThree{
	public static void main(String[]args){
		
		String answer2= "yes";//answer input for a loop of coefficients
		double x,a,b,c,d,e,f,y; //creates variables
		Scanner input= new Scanner(System.in); //creates scanner
		while(answer2.equals("yes")){
			System.out.print("Enter Coefficient 5: ");//enter numbers for coefficients
			f= input.nextDouble();
			System.out.print("Enter Coefficient 4: ");
			e= input.nextDouble();
			System.out.print("Enter Coefficient 3: ");
			d= input.nextDouble();
			System.out.print("Enter Coefficient 2: ");
			c= input.nextDouble();
			System.out.print("Enter Coefficient 1: ");
			b= input.nextDouble();
			System.out.print("Enter Coefficient 0: ");
			a= input.nextDouble();
			String answer= "yes";
			while(answer.equals("yes")){			//loop for entering a value of x
				System.out.print("Enter x: ");
				x= input.nextDouble();
				y= a+b*x+c*Math.pow(x,2)+d*Math.pow(x,3)+e*Math.pow(x,4)+f*Math.pow(x,5);
			
				System.out.printf("Value is: %.0f",y);
				System.out.print("\nEvaluate another value of x? ");
				answer=input.next(); //reads entire line
				System.out.println("");
			}
		
			System.out.print("Another Polynomial? ");
			answer2= input.next();
					
		}
		System.out.println("Thanks, end of program.");
	}
}