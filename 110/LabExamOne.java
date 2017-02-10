import java.util.*;

//Steve Delgado
//Comp 110 lab exam 1
//3/20/19

public class LabExamOne{

	public static void show(int[]data){						//show method to show the values in the array
		for(int i=0;i<data.length;i++){		
			System.out.print(data[i]+" ");					//loop to print out values of array
		}
		System.out.println();
	}
	public static void generate(int[]data,int range){ 	//generate method to generate random numbers in array
		Random number = new Random();							//random numbers
		for(int i=0;i<data.length;i++){						//loop to generate random numbers
			data[i]=number.nextInt(range);
		}
		System.out.println();		
	}
	
	public static void swap(int[]data, int a, int b){	//swap method to swap the values of data[a] with the value of data[b]
		int temp= data[a];										//creates a temporary storage for data[a]
		data[a]=data[b];											//swap data a with data b
		data[b]=temp;												//data b now swaps with data[a]
	}
	
	public static void reverse(int[]data){					//method to reverse array			
		int half=data.length/2;
		for(int i=0;i<half;i++){
			swap(data,i,data.length-1-i);
		}
	}
	public static int secondmax(int[]data){				//The method finds and returns the second largest value in the array.
		int m1= data[0];
		int m2= data[1];
		if(m2>m1){
			int temp=m1 ;											//creates a temporary storage for m1
			m1=m2;													//assign m2 to m1
			m2=temp;													//assign m1 to m2
		}
		for(int i=2;i<data.length;i++){						//loop to search the array from 2 to the end of array
			if(data[i]>m1){										//check to see if any values in the array are greater than m1 if its true run if statements
				m2=m1;												//swap m2 with m1
				m1=data[i];											//swap m1 with the value of data[i]
			}
			else if(data[i]>m2){									//if a value in the index is greater than m2 do the else if statements
				m2=data[i];											//swap m2 with the value of data[i]
			}
		}			
		return m2;
	}	
	
	public static void main(String[]args){
	int[]x= new int[10];
	generate(x,100);
	show(x);
	swap(x,3,7);										
	show(x);
	reverse(x);
	show(x);
	int y=secondmax(x);
	System.out.println(y);
	}

}
