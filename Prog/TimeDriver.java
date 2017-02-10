public class TimeDriver{
	public static void main(String[]args){
		Time t= new Time();
		
		System.out.println(t.toString());
		Time u = new Time(32,59);
		System.out.println(u.toString());
	}
}