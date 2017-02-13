import java.util.*;

public class ArrayDemo2{

	//method print given a string and an array of data prints both out.
	public static void print(String s,int[] data){
		System.out.print(s);
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" "); //iterate through array and print it out.
		}
		System.out.println();
	}

	//method to shift data 1 element to the left
	public static void lcs(int[] data){
		int temp = data[0];
		for(int i=0;i<data.length-1;i++){
			data[i]=data[i+1];
		}
		data[data.length-1]=temp;
	}

	//method to shift element to the right
	public static void rcs(int[]data){
		int temp = data[data.length-1];
		for(int i=data.length-2;i>=0;i--){
			data[i+1]=data[i];
		}
		data[0]=temp;
	}


	public static void main(String[]args){
		int[]a={3,5,7,8,2};
		int[]b={3,5,7,8,2};
		print("before: ",a);
		lcs(a); //calls left shift method.
		print("after: ",a);
		rcs(b); //calls right shift method.
		print("after: ",b);
	}
}
