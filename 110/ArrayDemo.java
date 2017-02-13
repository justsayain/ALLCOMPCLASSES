import java.util.*;

public class ArrayDemo{

	//method randfil():
	//create an array, fill with random numbers, return it
	public static int[] randfill (int n, int range){

		//create array of size n to be returned
		int []data=new int[n];

		// fill aray with random numbers from 0 to range -1
		Random random = new Random();

		for(int i=0;i<data.length;i++){		//data.length means the same as n in int[n]
			data[i] = random.nextInt(range);
		}
		return data;
	}

	//method show();
	//prints but the contents of an array to the display
	public static void show(int[]data){		//stub empty skeleton
		for(int i=0;i<data.length;i++){		//data.length means the same as n in int[n]
			System.out.print(data[i]+" ");	//same loop in order to print out values of previous method
		}
		System.out.println();
	}

	// method min():
	// finds the min value in an arry and returns int
	public static int min(int [] data){
		int m = data[0];
		for(int i=1; i<data.length;i++){
			if(data[i]<m)
			m=data[i];
		}
		return m;
	}
	public static int minindex(int [] data){
		int m = data[0];
		for(int i=1; i<data.length;i++){
			if(data[i]<m)
			m=data[i];
		}
		return m;
	}

	//look for a value in an array linear(from left to right)
	public static boolean linearsearch(int [] data, int x){
		for(int i=0;i<data.length-1;i++){
			if(x==data[i])
				return true;//returns true for a match inside the loop(inside the index)
		}
		return false;//returns false after the loop has looked at all the index and does not match x
	}

	//void method because it does not need to return
	public static void findnreplace(int []data, int x, int y){
		for(int i=0; i<data.length; i++){
			if(x==data[i])
				data[i]=y;
		}
	}


	// method max():
	// finds the max value in an arry and returns int
	public static int max(int [] data){
		return 0;
	}
	// method to call swap
	public static void swap(int x,int y){
		System.out.println("swap method is being called	.");
		int temp=x;
		x=y;
		y=temp;
		System.out.print("a= "+x+", b= "+y);
	}
	// swap a number in the index of an array
	public static void swap(int[] data, int index1,int index2){
		int temp= data[index1];
		data[index1]=data[index2];
		data[index2]=temp;
	}
	//factorial
 	public static int factorial(int n){
		int y=1;
		for(int i=1;i<=n;i++){	//iterative solution (use loop to get solution)
			y*=i;
		}
		return y;
	}

	//factorial using recursive
	public static int fact2(int n){
		//use factoral loop
		if (n==0)
			return 1;
		else
			return n*fact2(n-1);
	}


	public static void main(String[]args){
		System.out.print("array x[]: ");
		int[] x = randfill(10,1000);
		show(x);
		System.out.println("min(x) = " + min(x));
		int[]z = {22,19,87,6,35};
		System.out.print("\narray z[]: ");
		show(z);
		System.out.println(3 + ": "+ linearsearch(z,3)); //is 3 in z?
		System.out.println(22+ ": "+ linearsearch(z,22)); //is 22 in z?
		int a=1;
		int b=2;
		System.out.println("\na = "+a+"\nb = "+b);
		swap(a,b);
		System.out.println("\n\nf(3)= "+fact2       (3));
	}
}
