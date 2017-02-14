/* Programmer: Steve Delgado
Project 1: Part 3 */
import java.util.*;
public class Addition1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n1, n2, sum;
		System.out.prin("Enter the first integer: ");
		n1 = input.nextInt();
		System.out.print("Enter the second integer: ");
		n2 = input.nextInt();
		sum = n1 + n2;
		System.out.println("The sum of the values is " + sum);
}
}

