import java.util.*;
import java.io.*;
public class Calculator{
	//data
	public static int maxsymbols=100;
	public static String [] symbols = new String[maxsymbols];
	public static int[] values= new int[maxsymbols];
	public static int symbolcount=0;
	
	// 2 helper methods
	public static int lookup(String s) { 												//looks to see whether there is a variable with a value in the array.
		for(int i=0; i<symbolcount;i++){
			if(s.equals(symbols[i]))
				return i;
		}
		return -1;
	}
	public static void addsymbol(String s) {
		int result=lookup(s);
		if (result==-1){
			symbols[symbolcount]=s;
			symbolcount++;
		}
		else
			symbols[result]=s;
		 
	}
	public static int eval(String s) { 
		char c= s.charAt(0);
		if(Character.isDigit(c))
			return Integer.parseInt(s);
		else{
			int x = lookup(s);
			if(x==-1)
				System.out.print("error cannot find variable");
			else
				return values[x];
		}
		return 0000;
	}
	public static int eval(String s2, String op, String s3) {
		int a=eval(s2);
		int b= eval(s3);
		if(op.equals("+"))
			return a+b;
		else if(op.equals("-"))
			return a-b;
		else if(op.equals("*"))
			return a*b;
		else if(op.equals("/"))
			return a/b;
		else return 00000;  
	}
	
	public static int getsymbolvalue(String s) { 
		for(int i=0; i<symbolcount;i++){
			if(s.equals(symbols[i]))
				return values[i];
		}
		return 0000;
	}
	public static void setsymbolvalue(String s, int v) { 
		int result=lookup(s);
		if (result==-1){
			symbols[symbolcount]=s;
			values[symbolcount]=v;
			symbolcount++;
		}
		else
			values[result]=v;
	}
	public static String[] tokenize(String s) { 
	String []t=s.split(" ");
	return t;
	}
	public static void show() {
		if(symbolcount ==0)
			System.out.println("table is empty");
		else
			for(int i=0; i<symbolcount;i++){
				System.out.println(symbols[i]+" = "+values[i]);			
		} 
	}
	public static void help() { 
		System.out.println("Enter a variable like this: x = #, in order to put in a number into the variable");
		System.out.println("Once variables are stored you can add them together i.e z = x + y");
		System.out.println("Able to replace an already stored variable with another number");	
	}
	public static boolean checkinput(String[] t) { 
	return true;
	}
	
	
	// 3 driver methods
	public static void commandline() throws Exception { 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String prompt1 = "Calculator 1.0";
		String prompt2 = ">> ";
		System.out.println(prompt1);
		System.out.print(prompt2);
		String line = in.readLine();
		while (! line.equals("quit")) {
			String[] tokens = tokenize(line);
			boolean okay = checkinput(tokens);
			if (okay) {
				if (tokens.length==1) {
					if (tokens[0].equals("help")) help();
						else if (tokens[0].equals("show")) show();
					}
				else if (tokens.length == 3) {
					int v = eval(tokens[2]);
					setsymbolvalue(tokens[0],v);
					System.out.println("");
				}
			else {
				int v = eval(tokens[2],tokens[3],tokens[4]);
				setsymbolvalue(tokens[0],v);
				System.out.println("");
			}
			}
			System.out.print(prompt2);
			line = in.readLine();
		}
		System.out.println("Powering Calculator down");
		for(int x=10;x>0;x--){
			for(int y=x;x>0;y--){
			System.out.println("-");
			}
			System.out.println();
		}	
	}
	
	public static void main(String[] args) throws Exception { 
		commandline();
	}
	
}