//Simple Demo to show how String functions can be used.
import java.util.*;

public class StringDemo{

	public static void main(String[]args){
		String s = "xyzpqrabcdefghifj";
		char x = s.charAt(3); //get character from string s at position 3
		System.out.println("s = "+s);
		System.out.println("s[3] using charAt function: 	"+x);

		int y = s.length(); //built in length function to obtain size of string.
		System.out.println("length of string s = "+y);

		String t= s.substring(3,7); //creates string from beginning and ending positions.
		System.out.println("t= "+t);
		String u= s.substring(4); //creates string from given position.
		System.out.println("u= "+u);
	}
	//unfinished
	// public String toString(){
	// 	String s= "";
	// 	s = s + this.real+ " + " + this.imaginary+"i";					//real #(3,5) ---- 3+5i
	// 	return s;
	// }
}
