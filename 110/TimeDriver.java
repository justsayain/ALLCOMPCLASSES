//Program to work with Time.java File
//Simple Driver that contatins main method.
public class TimeDriver{

	public static void main(String[]args){

		Time t= new Time();

		System.out.println(t.toString());
		Time u = new Time(22,59);
		System.out.println(u.toString());
	}
}
