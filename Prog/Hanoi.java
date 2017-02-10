import java.util.*;
public class Hanoi{
	////////////////////////////////////////////////////
	// solve (); recursive solution to Tower of Hanoi //
	// for n disk in terms of n-1 disks					  //
	// prints moves out to the display					  //
	////////////////////////////////////////////////////
	public static void solve(int n, int src, int dst, int spare){
		// base case
		if(n==1)
			System.out.println(src+" to "+dst);
		// recursive case
		else{
			solve(n-1, src, spare, dst);	//swap dst and spare
			solve(1,src, dst,spare);		
			solve(n-1,spare, dst,src);
		}
	}
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int moves;
		
		int a=1;
		int b=2;
		int c=3;
		// test case: args[0]
		solve(Integer.parseInt(args[0]),a,b,c);
	}
}