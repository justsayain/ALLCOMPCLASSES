import java.util.*;

//Simple Craps game
public class CrapsDemo {
	public static int roll(){
		Random r= new Random();
		return r.nextInt(6)+r.nextInt(6) +2;
	}

	public static boolean craps(){
		int roll1=roll();
		//System.out.print(roll1+ " ");

		if(roll1==7 || roll1==11){
			//System.out.println("you won!");
			return true;
		}
		else if(roll1==2 ||roll1==3 ||roll1==12){
			//System.out.println("youlose!");
			return false;
		}
		else{
			int roll2=roll();
			//System.out.print(roll2 + " ");
			while(!(roll2==roll1 || roll2==7)){
				roll2=roll();
				//System.out.print(roll2 + " ");
			}
		if(roll2==roll1)
			//System.out.println("you won!");
			return true;
		else
			//System.out.println("you lose!");
			return false;
		}
	}
	public static void main(String[]args){
		Scanner input= new Scanner(System.in);
		System.out.print("how many games would you like to play? ");
		int games= input.nextInt();
		for(int i=1; i<=games;i++){
			if(craps())
				System.out.println("you won!");
			else
				System.out.println("you lose :(");
		}
	}
}
