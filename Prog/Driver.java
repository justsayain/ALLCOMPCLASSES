public class Driver{
	public static void main(String[]args){
		Date x = new Date();								//declare reference variable
		x.month=4;
		x.day=1;
		x.year=2013;
		Date d= new Date(4,1,2013);
		Date e= new Date();
		Date f= new Date(9,1,1987);
		System.out.print(d);
	}
}