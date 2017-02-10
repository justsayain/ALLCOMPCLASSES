public class ExcDemo{
	public static void main(String[] args){
		int[] data= new int[10];
		try{
		for(int i=0;i<=data.length;i++)
			data[i]=0;
		}
		catch(Exception e){
			System.out.println("exception occured");
		}
			
	}
}