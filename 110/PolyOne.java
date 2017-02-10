/* Programmer: Steve Delgado
Comp 110/110L Spring 2013
Project 3: Part 1
Polygon Evaluator Version 1 */

import java.util.*;
public class PolyOne{
	public static void main(String[]args){
		double x,a,b,c,d,e,f,y;
		Scanner input= new Scanner(System.in);
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
		System.out.print("Enter x: ");
		x= input.nextDouble();
		
		y= a+b*x+c*Math.pow(x,2)+d*Math.pow(x,3)+e*Math.pow(x,4)+f*Math.pow(x,5);
		System.out.printf("Value is: %.1f",y);
	}
}