import java.util.*;
public class ArrayDemo2{
	public static void print(String s,int[] data){
		System.out.print(s);
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
		System.out.println();
	}
	public static void lcs(int[] data){
		int a=data[0];		
		for(int i=0;i<data.length-1;i++){
			data[i]=data[i+1];
		}
		data[data.length-1]=a;
	}
	public static void rcs(int[]data){
		int a = data[data.length-1];
		for(int i=data.length-2;i>=0;i--){
			data[i+1]=data[i];
		}
		data[0]=a;
	}
	
	
	public static void main(String[]args){
	int[]a={3,5,7,8,2};
	int[]b={3,5,7,8,2};
	print("before: ",a);
	lcs(a);
	print("after: ",a);
	rcs(b);
	print("after: ",b);
	}
}