import java.util.*;

public class StringDemo2{

	public static int count(String s){
		int numspaces=0;
		for(int i = 0; i<s.length();i++){
			if(s.charAt(i)==' ')
				numspaces++;
		}
		return numspaces+1;
	}
	public static String[] split(String s){
		 String []result= new String[count(s)];
		 
		 int j=0;
		 for(int i=0; i<result.length;i++){
		 	result[i]= "";
			while(j<s.length()&& s.charAt(j) != ' '){
				result[i] += s.charAt(j);
				j++;
			}
			j++;
		 }
		  
		 return result;
	}
		
	public static void main(String[]args){
		String s= "abc def ghi";
		System.out.println(count(s));
		String [] t = split(s);
		for(int i = 0; i<t.length;i++){
			System.out.println(i+": "+t[i]);
		}
	}
}