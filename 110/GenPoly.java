/* Programmer: Steve Delgado
Comp 110/110L Spring 2013 Mon/Wed 8-11am
Project 3: Part 3
Polygon Evaluator Version 3 */

import java.util.*;

public class GenPoly{
	public static void main(String[]args){
		Scanner input = new Scanner(System.in);
		double [] c;
		double y=0,x;
		int degree;
		System.out.print("Enter Degree of Polynomial: ");
		degree= input.nextInt();
		c= new double[degree+1];
		for (int i=0;i<c.length;i++){
			System.out.print("Please Enter Coefficient "+i+": "); 
			c[i] = input.nextDouble();
		}
		System.out.print("Enter x: ");
		x = input.nextDouble();
		for(int i=0;i<c.length;i++){
			
			y += c[i]*Math.pow(x,i);
		}
		System.out.println("Value: "+y);
	}
}		