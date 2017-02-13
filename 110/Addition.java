/* Programmer: Steve Delgado
Project 1: Part 3 */

import java.util.*;
public class Addition {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n1, n2, sum;
		System.out.println("Comp 110 Project 1, Part 3\nAddition of two number provided by user input.");
		System.out.print("Enter the first integer: ");
		n1 = input.nextInt();
		System.out.print("Enter the second integer: ");
		n2 = input.nextInt();
		//Provides the sum of two given integers.
		sum = n1 + n2;
		System.out.println("The sum of the values is " + sum);
	}
}
