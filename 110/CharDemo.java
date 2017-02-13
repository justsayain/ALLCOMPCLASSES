import java.util.*;

public class CharDemo{
	public static void main (String[]args){
		char x= 'a';
		int y= (int)'a';
		//Provide an example of a character and a cast of an integer
		//a casts to ascii number.
		System.out.println("Char a = "+x);
		System.out.println("int  a = "+y);

		String s="this is a string 123 456";
		//need to use escape character \ to be able to put quotes inside quotes.
		System.out.print("s= "+"\""+s+"\"\n");
		//array t has string s that has been split to elements based on space character.
		String [] t= s.split(" ");
		//Prints out array t to show how it has been split.
		for(int i=0; i<t.length;i++){
			System.out.println("t["+i+"]= "+t[i]);
		}
		int z = Integer.parseInt(t[4])+Integer.parseInt(t[5]);
		System.out.println("t[4]+t[3]= "+z);
	}
}
