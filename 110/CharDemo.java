import java.util.*;

public class CharDemo{
	public static void main (String[]args){
		char x= 'a';
		int y= (int)'a';
		System.out.println("Char x = "+x);
		System.out.println("int y = "+y);
		
		
	
		
		String s="this is a string 123 456";
		System.out.print("s= "+"\""+s+"\"");
		
		String [] t= s.split(" ");
		for(int i=0; i<t.length;i++){
		System.out.println("t["+i+"]= "+t[i]);	
		}
		int z = Integer.parseInt(t[4])+Integer.parseInt(t[5]);
		System.out.println(z);
	}
}