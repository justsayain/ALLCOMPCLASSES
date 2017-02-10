/* Programmer: Steve Delgado
Comp 110/110L Spring 2013
Project 3: Part 3
Polygon Evaluator Version 3 */

import java.util.*;
public class PolyThree{
	public static void main(String[]args){
		String answer= "yes";
		double x,a,b,c,d,e,f,y; //creates variables
		Scanner input= new Scanner(System.in); //enter numbers for coefficients 
		System.out.print("Enter Coefficient 5: ");
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
		
		while(answer.equals("yes")){
			System.out.print("Enter x: ");
			x= input.nextDouble();
			y= a+b*x+c*Math.pow(x,2)+d*Math.pow(x,3)+e*Math.pow(x,4)+f*Math.pow(x,5);
			
			System.out.printf("Value is: %.0f",y);
			System.out.print("\nEvaluate another value of x? ");
			answer=input.next(); //reads entire line
			System.out.println("");
		}
		
		System.out.print("Another Polynomial? ");
		answer= input.next();
		while(answer.equals("yes")){
			System.out.print("Enter Coefficient 5: ");
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
			while(answer.equals("yes")){
			System.out.print("Enter x: ");
			x= input.nextDouble();
			y= a+b*x+c*Math.pow(x,2)+d*Math.pow(x,3)+e*Math.pow(x,4)+f*Math.pow(x,5);
			
			System.out.printf("Value is: %.0f",y);
			System.out.print("\nEvaluate another value of x? ");
			answer=input.next(); //reads entire line
			System.out.println("");
			}
		System.out.print("Another Polynomial? ");
		answer= input.next();
		}
		System.out.println("Thanks, end of program.");
	}
		
	}