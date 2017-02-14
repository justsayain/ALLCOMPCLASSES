/* Programmer: Steve Delgado
Comp 110/110L Spring 2013
Project 2: Part 2
Mortgage Interest
Java program that evaluates quadratic equation.
x = (-b+-sqrt(b^2-4ac))/2a
*/

import java.util.*;
public class QuadraticEquations{
	public static void main(String[]args){

		Scanner input=new Scanner(System.in);
		//create variables
		double a,b,c,r1,r2;

		//obtain inputs
		System.out.print("Enter coefficient a: ");
		a=input.nextDouble();
		System.out.print("Enter coefficient b: ");
		b=input.nextDouble();
		System.out.print("Enter coefficient c: ");
		c=input.nextDouble();

		//compute results
		r1=(-b+Math.sqrt(b*b-4*a*c))/(2*a);
		r2=(-b-Math.sqrt(b*b-4*a*c))/(2*a);
		//output results
		System.out.println("The first root is: "+r1);
		System.out.println("The second root is: "+r2);
	}
}
