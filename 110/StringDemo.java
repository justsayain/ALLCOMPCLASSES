import java.util.*;
public class StringDemo{
	public static void main(String[]args){
		String s= "xyzpqrabcdefghifj";
		char x= s.charAt(3);
		System.out.println("s= "+s);
		System.out.println("x= "+x);
		
		int y=s.length();
		System.out.println("length of string s = "+y);
		
		String t= s.substring(3,7);
		System.out.println("t= "+t);
		String u= s.substring(4);
		System.out.println("u= "+u);
	}
	public String toString(){
		String s= "";
		s=s+this.real+" + "+this.imaginary+"i";					//real #(3,5) ---- 3+5i
		return s;
	}
}