/*
Programmer: Steve Delgado
Simple java program to output a pyramid of stars
using two for loops.
*/
public class stars{
	public static void main(String[]args){
	 	int n=6;
		for(int i=1;i<=n;i++){
			for(int j=0;j<i;j++){
				System.out.print("*");
		}
			System.out.println();
		}
	}
}
